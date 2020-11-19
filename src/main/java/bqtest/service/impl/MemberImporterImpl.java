package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberImporter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MemberImporterImpl implements MemberImporter {

    public List<Member> importMembers(File inputFile) throws IOException {
        return Files.lines(inputFile.toPath())
                .map(line -> {
                   //TODO implement here

                    Member  member = new Member();
                        member.setId(line.substring(0, 12));
                        member.setLastName(line.substring(12, 12 + 25));
                        member.setFirstName(line.substring(12 + 25, 12 + 25 + 25));
                        member.setAddress(line.substring(12 + 25 + 25, 12 + 25 + 25 + 30));
                        member.setCity(line.substring(12 + 25 + 25 + 30, 12 + 25 + 25 + 30 + 20));
                        member.setState(line.substring(12 + 25 + 25 + 30 + 20, 12 + 25 + 25 + 30 + 20 + 4));
                        member.setZip(line.substring(12 + 25 + 25 + 30 + 20 + 4, 12 + 25 + 25 + 30 + 20 + 4 + 5));
                    //TODO replace + operator with final value once the file is parsed successfully
                    return member;
                }).collect(Collectors.toList());
    }

}
