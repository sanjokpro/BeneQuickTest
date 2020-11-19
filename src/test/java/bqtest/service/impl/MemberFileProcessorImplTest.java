package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberFileProcessor;
import bqtest.service.MemberImporter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MemberFileProcessorImpl.class, MemberImporterImpl.class})

@SpringBootTest
public class MemberFileProcessorImplTest {
    private File inputFile;

    @Autowired
    private MemberFileProcessor memberFileProcessor;
    @Autowired
    private MemberImporter memberImporter;

    @Before
    public void setUp() {
        initMocks(this);
        inputFile = new File("Members.txt");
    }

    @Test
    public void processFile() throws Exception {
        inputFile = new File("Members.txt");
        Map<String, List<Member>> stringListMap = memberFileProcessor.processFile(inputFile);
        //verify map contains keys of 5 states as set
        Assert.assertEquals(5, stringListMap.keySet().size());
    }

    @Test
    public void getNonDuplicateMembers() throws Exception {
        List<Member> members = memberImporter.importMembers(inputFile);
        List<Member> getNonDuplicateMembers = ReflectionTestUtils.invokeMethod(memberFileProcessor, "getNonDuplicateMembers", members);
        Assert.assertEquals(100, getNonDuplicateMembers.size());
    }

    @Test
    public void testSplitMembersByState() throws Exception {
        List<Member> members = memberImporter.importMembers(inputFile);
        Map<String, List<Member>> splitedMap = ReflectionTestUtils.invokeMethod(memberFileProcessor, "splitMembersByState", members);
        //since original file was use for test also total unique states should be 5
        Assert.assertEquals(5, splitedMap.keySet().size());

    }

    @Test
    public void testGetMemberImporter() {
        Object getMemberImporter = ReflectionTestUtils.invokeMethod(memberFileProcessor, "getMemberImporter");
        Assert.assertEquals("bqtest.service.impl.MemberImporterImpl", getMemberImporter.getClass().getName());

    }
}
