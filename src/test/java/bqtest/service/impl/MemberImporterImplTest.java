package bqtest.service.impl;

import bqtest.service.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MemberFileProcessorImpl.class, MemberImporterImpl.class})
@SpringBootTest
public class MemberImporterImplTest {
    @Autowired
    private MemberImporterImpl memberImporter;

    @Before
    public void setUp() {
    }

    @Test
    public void testImportMembers() throws Exception {
        // Setup
        final File inputFile = new File("Members.txt");

        // Run the test
        List<Member> members = memberImporter.importMembers(inputFile);


        // Verify the results
        assertEquals(108, members.size());
    }

    @Test(expected = IOException.class)
    public void testImportMembers_ThrowsIOException() throws Exception {
        // Setup
        final File inputFile = new File("JPT-file-name.txt");
        // Run the test
        memberImporter.importMembers(inputFile);
        // @Test(expected = IOException.class)

    }
}
