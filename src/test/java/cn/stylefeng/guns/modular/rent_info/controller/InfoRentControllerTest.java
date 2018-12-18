package cn.stylefeng.guns.modular.rent_info.controller;

import cn.stylefeng.guns.GunsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= GunsApplication.class)
public class InfoRentControllerTest {
    @Autowired InfoRentController infoRentController;
    @Test
    public void test()
    {
        infoRentController.list(null,null,null);
    }

}