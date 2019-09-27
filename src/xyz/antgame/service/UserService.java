package xyz.antgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.antgame.controller.PlayRoom;
import xyz.antgame.controller.UserInterface;
import xyz.antgame.domain.GameResultSet;

//    这个类感觉应该不需要 用户接口应该有PlayRoom来实现
//      输入还是需要与playRoom分离 否则逻辑比较奇怪
//      需要把所有的游戏相关属性全部存到PlayRoom类中 并且函数调用也不符合设计和现实

@Service
public class UserService implements UserInterface {
    private PlayRoom playRoom;

    @Override
    @ResponseBody
    @RequestMapping("/startGame")
    public GameResultSet startGame(UserRequest userRequest) {
        if(playRoom==null){
            PlayRoom playRoom = new PlayRoom();
        }


        String[] tempString = userRequest.getAntDirections().split(" ");
//        处理请求

//        处理结束

        return null;
    }

    @Override
    @ResponseBody
    @RequestMapping("/startSimulation")
    public GameResultSet startSimulation(UserRequest userRequest) {
        return null;
    }

    @Override
    @RequestMapping("/reset")
    public void resetSimulationStatus() {

    }
}
