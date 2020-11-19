package bqtest.web;

import bqtest.service.MemberFileProcessor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    MemberFileProcessor mockMemberFileProcessor;
    private FileController fileControllerUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        fileControllerUnderTest =new FileController(mockMemberFileProcessor);
    }

    @Test
    public void testLoadData() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/load-data"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test(expected = Exception.class)
    public void testLoadData_MemberFileProcessorThrowsException() throws Exception {
        // Setup
        when(mockMemberFileProcessor.processFile(new File("filename.txt"))).thenThrow(Exception.class);

        // Run the test
        fileControllerUnderTest.loadData("fileToLoad");
    }
}
