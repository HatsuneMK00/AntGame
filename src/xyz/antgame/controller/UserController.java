package xyz.antgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.antgame.service.PlayRoom;
import xyz.antgame.service.UserInterface;
import xyz.antgame.domain.GameResultSet;
import xyz.antgame.domain.UserRequest;

//    这个类感觉应该不需要 用户接口应该有PlayRoom来实现
//      输入还是需要与playRoom分离 否则逻辑比较奇怪
//      需要把所有的游戏相关属性全部存到PlayRoom类中 并且函数调用也不符合设计和现实


//      注意前端传值的时候 所有数字间用空格分隔
//      解决前后端不知道怎么出来的 跨域访问问题
@CrossOrigin
@Controller
public class UserController implements UserInterface {
    private PlayRoom playRoom;

    @Override
    @ResponseBody
    @RequestMapping(value = "/startGame")
    public GameResultSet startGame(UserRequest userRequest) {
        if (playRoom == null) {
            this.playRoom = new PlayRoom();
        }
        int antNum, incTime, poleLength, i;
        GameResultSet gameResultSet;

        int [] antPositions;
        int [] antDirections;
//        这里没想好要不要把处理请求数据的代码提取出去变成方法
//        提取出去的话 有一些不直观 也有可能不太符合逻辑
//        不提取出去会有冗余代码
        if(userRequest.getAntPositions()!=null){
//        处理请求
            antNum = userRequest.getAntNum();
            incTime = userRequest.getIncTime();
//        处理结束
            antDirections = new int[antNum];
            antPositions = new int[antNum];
            //        处理请求
            String[] tempString = userRequest.getAntDirections().split(" ");
            for (i = 0; i < antNum; i++) {
                antDirections[i] = Integer.parseInt(tempString[i]);
            }
            tempString = userRequest.getAntPositions().split(" ");
            for (i = 0; i < antNum; i++) {
                antPositions[i] = Integer.parseInt(tempString[i]);
            }
            poleLength = userRequest.getPoleLength();
//        处理结束
        }else{
            antNum = 5;
            incTime = 1;
            antPositions = new int[]{30,60,90,120,150};
            antDirections = new int[]{-1,1,-1,1,-1};
            poleLength = 200;
        }
//        为playRoom对象注入属性值
        playRoom.setIncTime(incTime);
        playRoom.setAntNum(antNum);
//        自定义游戏才有的属性
        playRoom.setAntDirections(antDirections);
        playRoom.setAntPositions(antPositions);
//        结束
        playRoom.setPoleLength(poleLength);
//        注入结束

        gameResultSet = playRoom.startGame();

        return gameResultSet;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/startSimulation")
    public GameResultSet startSimulation(UserRequest userRequest) {
        if (playRoom == null) {
            this.playRoom = new PlayRoom();
        }
        int antNum, incTime, poleLength, i;
        GameResultSet gameResultSet;

        int [] antPositions;
//        处理请求 当第二次传值时 暂时认为前端会把这些值一起传过来 但是不能更改
//        当没有参数的时候 用于绑定参数的对象里面是没东西的 但是那个参数对象是创建了的
        if (userRequest.getAntPositions() != null) {
            antNum = userRequest.getAntNum();
            incTime = userRequest.getIncTime();
//        处理结束
            antPositions = new int[antNum];
            //        处理请求
            String[] tempString;
            tempString = userRequest.getAntPositions().split(" ");
            for (i = 0; i < antNum; i++) {
                antPositions[i] = Integer.parseInt(tempString[i]);
            }
            poleLength = userRequest.getPoleLength();
//        处理结束
        } else {
            antNum = 5;
            incTime = 1;
            antPositions = new int[]{30,60,90,120,150};
            poleLength = 200;
        }
//        为playRoom对象注入属性值 这些值在第二次调用之后都不会改变
        playRoom.setIncTime(incTime);
        playRoom.setAntPositions(antPositions);
        playRoom.setAntNum(antNum);
        playRoom.setPoleLength(poleLength);
//        注入结束

        gameResultSet = playRoom.startSimulation();
        return gameResultSet;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/reset")
    public GameResultSet resetSimulationStatus() {
        playRoom.setAntDirectionsIterator(null);
        playRoom.setMinTime(Integer.MAX_VALUE);
        playRoom.setMaxTime(Integer.MIN_VALUE);
        return new GameResultSet();
    }
}
