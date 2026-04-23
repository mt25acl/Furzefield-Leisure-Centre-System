package flc.service;

import flc.model.Member;

import java.util.*;

/**
 * Manages the registry of FLC members.
 */
public class MemberService {

    private final Map<String, Member> members = new LinkedHashMap<>();
    private int memberCounter = 1;

    /**
     * Registers a new member with an auto-generated ID.
     */
    public Member registerMember(String name, String email) {
        String id = "M" + String.format("%03d", memberCounter++);
        Member member = new Member(id, name, email);
        members.put(id, member);
        return member;
    }

    /**
     * Finds a member by their ID.
     */
    public Optional<Member> findMemberById(String memberId) {
        return Optional.ofNullable(members.get(memberId));
    }

    /**
     * Finds a member by their name (case-insensitive).
     */
    public Optional<Member> findMemberByName(String name) {
        return members.values().stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public Collection<Member> getAllMembers() {
        return Collections.unmodifiableCollection(members.values());
    }
}

