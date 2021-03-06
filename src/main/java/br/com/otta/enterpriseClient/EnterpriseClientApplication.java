package br.com.otta.enterpriseClient;

import java.util.Collection;
import java.util.Scanner;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.otta.enterpriseApi.model.Enterprise;
import br.com.otta.enterpriseClient.service.EnterpriseService;

@SpringBootApplication
public class EnterpriseClientApplication implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(EnterpriseClientApplication.class);

    private Scanner scanner;
    private EnterpriseService service;

    @Inject
    public EnterpriseClientApplication(Scanner scanner, EnterpriseService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public static void main(String[] args) {
        LOG.debug("Application Startup.");
        SpringApplication.run(EnterpriseClientApplication.class, args);
        LOG.debug("Shutdown Application");
    }

    @Override
    public void run(String... args) throws Exception {
        int option;

        do {
            printMessages();
            option = scanner.nextInt();

            switch (option) {
            case 1:
                createEnterprise();
                break;
            case 2:
                updateEnterprise();
                break;
            case 3:
                printAllEnterprises();
                break;
            case 4:
                System.out.println("Encerrando aplicação.");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
            }
        } while (option != 4);

    }

    private void printMessages() {
        System.out.println("Escolha a opção:");
        System.out.println("1. Criar empresa");
        System.out.println("2. Editar empresa");
        System.out.println("3. Listar empresas");
        System.out.println("4. Sair");
    }

    private void createEnterprise() {
        System.out.println("Criar uma nova empresa.");
        System.out.println("Código da empresa:");
        long id = scanner.nextLong();
        System.out.println("Nome da empresa:");
        String name = scanner.next();
        name += scanner.nextLine();
        System.out.println("Tipo da empresa:");
        int typeId = scanner.nextInt();

        service.create(id, name, typeId);
        System.out.println("Empresa criada na Aplicação.");
    }

    private void updateEnterprise() {
        System.out.println("Atualizar uma empresa.");
        System.out.println("Código da empresa para editar:");
        long id = scanner.nextLong();
        System.out.println("Novo nome da empresa:");
        String name = scanner.next();
        name += scanner.nextLine();
        System.out.println("Novo tipo da empresa:");
        int typeId = scanner.nextInt();

        service.update(id, name, typeId);
        System.out.println("Empresa atualizada na Aplicação.");
    }

    private void printAllEnterprises() {
        Collection<Enterprise> enterpriseCollection = service.listAllEnterprises();
        String message = String.format("Empresas salvas na aplicação: %s.", enterpriseCollection.size());

        System.out.println(message);
        enterpriseCollection.stream().forEach(enterprise -> System.out.println(enterprise));
    }

}
