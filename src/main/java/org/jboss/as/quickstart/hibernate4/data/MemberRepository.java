/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstart.hibernate4.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.jboss.as.quickstart.hibernate4.model.Bond;

@ApplicationScoped
public class MemberRepository {

    @Inject
    private EntityManager em;

    public Bond findById(Long id) {
        return em.find(Bond.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Bond> findAllOrderedByName() {
        // using Hibernate Session and Criteria Query via Hibernate Native API
        Session session = (Session) em.getDelegate();
        Criteria cb = session.createCriteria(Bond.class);
        cb.addOrder(Order.asc("fund"));
        return (List<Bond>) cb.list();
        // return members;
    }

    public List<Bond> findAllOrderedByYear3() {
        // using Hibernate Session and Criteria Query via Hibernate Native API
        Session session = (Session) em.getDelegate();
        Criteria cb = session.createCriteria(Bond.class);
        cb.addOrder(Order.desc("year3"));
        return (List<Bond>) cb.list();
        // return members;
    }

}
