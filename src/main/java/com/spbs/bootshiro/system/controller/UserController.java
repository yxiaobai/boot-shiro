package com.spbs.bootshiro.system.controller;

import com.spbs.bootshiro.common.annotation.Log;
import com.spbs.bootshiro.common.controller.BaseController;
import com.spbs.bootshiro.common.domain.QueryRequest;
import com.spbs.bootshiro.common.domain.ResponseBo;
import com.spbs.bootshiro.common.utils.MD5Utils;
import com.spbs.bootshiro.system.domain.TUser;
import com.spbs.bootshiro.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    private static final String ON = "on";

    /**
     * 检测用户名是否可用(存在)
     * @param username
     * @param oldusername
     * @return
     */
    @RequestMapping("user/checkUserName")
    @ResponseBody
    public boolean checkUserName(String username, String oldusername) {
        if (StringUtils.isNotBlank(oldusername) && username.equalsIgnoreCase(oldusername)) {
            return true;
        }
        TUser result = this.userService.findByName(username);
        return result == null;
    }

    /**
     * 获取用户信息--含角色信息
     * @param userId
     * @return
     */
    @RequestMapping("user/getUser")
    @ResponseBody
    public ResponseBo getUser(Integer userId) {
        try {
            TUser user = this.userService.findById(userId);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    @Log("获取用户信息")
    @RequestMapping("user/list")
    @RequiresPermissions("user:list")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, TUser user) {
        return super.selectByPageNumSize(request, () -> this.userService.findUserWithDept(user, request));
    }


    @RequestMapping("user/regist")
    @ResponseBody
    public ResponseBo regist(TUser user) {
        try {
            TUser result = this.userService.findByName(user.getUserName());
            if (result != null) {
                return ResponseBo.warn("该用户名已被使用！");
            }
            this.userService.registUser(user);
            return ResponseBo.ok();
        } catch (Exception e) {
            log.error("注册失败", e);
            return ResponseBo.error("注册失败，请联系网站管理员！");
        }
    }


    @Log("新增用户")
    @RequiresPermissions("user:add")
    @RequestMapping("user/add")
    @ResponseBody
    public ResponseBo addUser(TUser user, Integer[] roles) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus())){
                user.setStatus(TUser.STATUS_VALID);
            }else {
                user.setStatus(TUser.STATUS_LOCK);
            }
            this.userService.addUser(user, roles);
            return ResponseBo.ok("新增用户成功！");
        } catch (Exception e) {
            log.error("新增用户失败", e);
            return ResponseBo.error("新增用户失败，请联系网站管理员！");
        }
    }

    @Log("修改用户")
    @RequiresPermissions("user:update")
    @RequestMapping("user/update")
    @ResponseBody
    public ResponseBo updateUser(TUser user, Integer[] roles) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus())){
                user.setStatus(TUser.STATUS_VALID);
            }else {
                user.setStatus(TUser.STATUS_LOCK);
            }
            this.userService.updateUser(user, roles);
            return ResponseBo.ok("修改用户成功！");
        } catch (Exception e) {
            log.error("修改用户失败", e);
            return ResponseBo.error("修改用户失败，请联系网站管理员！");
        }
    }

    @Log("删除用户")
    @RequiresPermissions("user:delete")
    @RequestMapping("user/delete")
    @ResponseBody
    public ResponseBo deleteUsers(String ids) {
        try {
            this.userService.deleteUsers(ids);
            return ResponseBo.ok("删除用户成功！");
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return ResponseBo.error("删除用户失败，请联系网站管理员！");
        }
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    @RequestMapping("user/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        TUser user = getCurrentUser();
        String encrypt = MD5Utils.encrypt(user.getUserName().toLowerCase(), password);
        return user.getPassword().equals(encrypt);
    }

    /**
     * 修改密码
     * @param newPassword
     * @return
     */
    @RequestMapping("user/updatePassword")
    @ResponseBody
    public ResponseBo updatePassword(String newPassword) {
        try {
            this.userService.updatePassword(newPassword);
            return ResponseBo.ok("更改密码成功！");
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return ResponseBo.error("更改密码失败，请联系网站管理员！");
        }
    }

    /**
     * 修改个人信息
     * @param model
     * @return
     */
    @RequestMapping("user/profile")
    public String profileIndex(Model model) {
        TUser user = super.getCurrentUser();
        user = this.userService.findUserProfile(user);
        String ssex = user.getSex();
        if (TUser.SEX_MALE.equals(ssex)) {
            user.setSex("性别：男");
        } else if (TUser.SEX_FEMALE.equals(ssex)) {
            user.setSex("性别：女");
        } else {
            user.setSex("性别：保密");
        }
        model.addAttribute("user", user);
        return "system/user/profile";
    }

    /**
     * 编辑资料    查看用户明细
     * @param userId
     * @return
     */
    @RequestMapping("user/getUserProfile")
    @ResponseBody
    public ResponseBo getUserProfile(Integer userId) {
        try {
            TUser user = new TUser();
            user.setId(userId);
            return ResponseBo.ok(this.userService.findUserProfile(user));
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return ResponseBo.error("获取用户信息失败，请联系网站管理员！");
        }
    }

    /**
     * 更改用户信息
     * @param user
     * @return
     */
    @RequestMapping("user/updateUserProfile")
    @ResponseBody
    public ResponseBo updateUserProfile(TUser user) {
        try {
            this.userService.updateUserProfile(user);
            return ResponseBo.ok("更新个人信息成功！");
        } catch (Exception e) {
            log.error("更新用户信息失败", e);
            return ResponseBo.error("更新用户信息失败，请联系网站管理员！");
        }
    }

    /**
     * 更改头像
     * @param imgName
     * @return
     */
    @RequestMapping("user/changeAvatar")
    @ResponseBody
    public ResponseBo changeAvatar(String imgName) {
        try {
            String[] img = imgName.split("/");
            String realImgName = img[img.length - 1];
            TUser user = getCurrentUser();
            user.setAvatar(realImgName);
            this.userService.updateNotNull(user);
            return ResponseBo.ok("更新头像成功！");
        } catch (Exception e) {
            log.error("更换头像失败", e);
            return ResponseBo.error("更新头像失败，请联系网站管理员！");
        }
    }
}
