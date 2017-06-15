package com.github.vlsidlyarevich.unity.common.audit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LoggerBasedAuditorTest {

    private LoggerBasedAuditor auditor;

    @Before
    public void setUp() {
        this.auditor = new LoggerBasedAuditor();
    }

    @Test
    public void logController_Success_IfValid() throws Exception {
        auditor.logController("username", "method", "path");
    }

    @Test(expected = IllegalArgumentException.class)
    public void logController_ExceptionThrown_IfMethodIsNull() throws Exception {
        auditor.logController("username", "", "path");
    }

    @Test(expected = IllegalArgumentException.class)
    public void logController_ExceptionThrown_IfPathIsNull() throws Exception {
        auditor.logController("username", "method", "");
    }

    @Test
    public void logService_Success_IfValid() throws Exception {
        auditor.logService("service name", "method", new Object[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void logService_ExceptionThrown_IfServiceNameIsNull() throws Exception {
        auditor.logService("", "method", new Object[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void logService_ExceptionThrown_IfMethodIsNull() throws Exception {
        auditor.logService("service name", "", new Object[]{});
    }
}
