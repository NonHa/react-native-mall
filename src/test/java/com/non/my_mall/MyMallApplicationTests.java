package com.non.my_mall;

import com.non.my_mall.dao.*;
import com.non.my_mall.dto.*;
import com.non.my_mall.mbg.model.*;
import com.non.my_mall.service.UmsRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MyMallApplicationTests {

    @Resource
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;

    @Resource
    private OmsOrderSettingDao orderSettingDao;

    @Resource
    private OmsOrderReturnApplyDao orderReturnApplyDao;

    @Resource
    private OmsCompanyAddressDao companyAddressDao;

    @Resource
    private OmsOrderReturnReasonDao orderReturnReasonDao;

    @Resource
    private UmsMenuDao menuDao;

    @Resource
    private UmsResourceCategoryDao resourceCategoryDao;

    @Resource
    private UmsRoleService roleService;
    @Test
    void getRole() {
        Long i = 1L;
        List<UmsMenu> umsMenus = roleService.listMenu(i);
        System.out.println("listWith"+umsMenus);
    }
    @Test
    void contextLoads() {
        List<PmsProductAttributeCategoryItem> listWith = productAttributeCategoryDao.getListWith();
        System.out.println("listWith"+listWith);
    }

    @Test
    void getOrderSetting() {
        OmsOrderSetting list = orderSettingDao.getList();
        System.out.println("listWith"+list);
    }

    @Test
    void getOrderReturnApply() {
        OmsOrderReturnApplyParam omsOrderReturnApplyParam = new OmsOrderReturnApplyParam();
        List<OmsOrderReturnApply> list = orderReturnApplyDao.getList(omsOrderReturnApplyParam);
        System.out.println("listWith"+list);
    }

    @Test
    void getCompanyAddress() {

        List<OmsCompanyAddress> list = companyAddressDao.getList();
        System.out.println("listWith"+list);
    }

    @Test
    void getOrderReason() {
        OmsOrderReturnReasonParam omsOrderReturnReasonParam = new OmsOrderReturnReasonParam();
        List<OmsOrderReturnReason> list = orderReturnReasonDao.getList(omsOrderReturnReasonParam);
        System.out.println("listWith"+list);
    }

    @Test
    void getMenu() {
        List<UmsMenuNode> trees = menuDao.getTrees();
        for (UmsMenuNode i:
             trees) {
            System.out.println("listWith"+i.getId());
        }

    }
    @Test
    void getResourceCategory() {
        List<UmsResourceCategoryWithChildren> tree = resourceCategoryDao.getTree();
        for (UmsResourceCategoryWithChildren i:
                tree) {
            System.out.println("listWith"+i.toString());
        }

    }
}
