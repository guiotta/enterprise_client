package br.com.otta.enterpriseClient.service;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.otta.enterpriseApi.api.EnterpriseApi;
import br.com.otta.enterpriseApi.model.Enterprise;
import br.com.otta.enterpriseApi.model.EnterpriseType;

@Service
public class EnterpriseService {
    private EnterpriseApi enterpriseApi;

    @Inject
    public EnterpriseService(EnterpriseApi enterpriseApi) {
        this.enterpriseApi = enterpriseApi;
    }

    public Enterprise create(long id, String name, int typeId) {
        EnterpriseType enterpriseType = EnterpriseType.findById(typeId);

        return enterpriseApi.saveEnterprise(new Enterprise(id, name, enterpriseType));
    }

    public Enterprise update(long id, String name, int typeId) {
        EnterpriseType enterpriseType = EnterpriseType.findById(typeId);

        return enterpriseApi.updateEnterprise(new Enterprise(id, name, enterpriseType));
    }

    public Collection<Enterprise> listAllEnterprises() {
        return enterpriseApi.listAllEnterprises();
    }

}
