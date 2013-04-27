package org.jboss.as.quickstart.hibernate4.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.as.quickstart.hibernate4.model.Bond;

@RequestScoped
public class MemberListProducer {
    @Inject
    private MemberRepository memberRepository;

    private List<Bond> members;

    @Produces
    @Named
    public List<Bond> getMembers() {
        return members;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Bond member) {
        retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        members = memberRepository.findAllOrderedByYear3();
    }

}
