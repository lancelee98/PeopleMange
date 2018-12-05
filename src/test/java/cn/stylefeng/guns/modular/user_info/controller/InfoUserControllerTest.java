package cn.stylefeng.guns.modular.user_info.controller;

import cn.stylefeng.guns.GunsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= GunsApplication.class)
public class InfoUserControllerTest {

    @Autowired InfoUserController infoUserController;
    @Test
    public void  test ()
    {
        infoUserController.detail(1);
    }
}