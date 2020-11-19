package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberFileProcessor;
import bqtest.service.MemberImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberFileProcessorImpl extends MemberFileProcessor {

    /*
     * Implement methods here
     */
    private final MemberImporter memberImporter;

    @Autowired
    public MemberFileProcessorImpl(MemberImporter memberImporter) {
        this.memberImporter = memberImporter;
    }

    @Override
    protected MemberImporter getMemberImporter() {
        return memberImporter;
    }

    @Override
    protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
        return new ArrayList<>(new HashSet<>(membersFromFile));
    }

    @Override
    protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
        Map<String, List<Member>> membersByState = new HashMap<String, List<Member>>();
        for (Member member : validMembers) {
            // checking if state name exist as key already for current member
            if (membersByState.containsKey(member.getState())) {
                // add member if State name exist as key
                membersByState.get(member.getState()).add(member);
            } else {
                List<Member> members = new ArrayList<Member>();
                members.add(member);
                membersByState.put(member.getState(), members);

            }

        }

        return membersByState;
    }

}
