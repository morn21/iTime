package online.morn.itime.controller.json;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/timeRecord")
@RestController
public class TimeRecordController {
    private Logger logger = Logger.getLogger(TimeRecordController.class);
}
