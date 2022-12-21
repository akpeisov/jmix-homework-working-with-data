package com.sample.carmarket.app;

import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarBean {
    @Autowired
    private DataManager dataManager;

    public Long getCarsCount(String engineType) {
        return dataManager.loadValue("select count(c.id)" +
                                               "  from Car c " +
                                               "  join Model m on c.model = m" +
                                               " where m.engineType = :engineType"
                                    ,Long.class)
                .parameter("engineType", engineType)
                .one();
    }
    //+
    //                                        "  join Model m on m = c.model and m.engine_type = 'G'"
    //:email")
    //            .parameter("email", "%@company.com")
}