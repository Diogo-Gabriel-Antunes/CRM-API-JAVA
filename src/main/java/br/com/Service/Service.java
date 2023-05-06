package br.com.Service;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;



@ApplicationScoped
public class Service {

    @Inject
    protected EntityManager em;

    protected Gson gson = new GsonUtil().parser;

    protected FieldUtil fieldUtil = new FieldUtil();
}
