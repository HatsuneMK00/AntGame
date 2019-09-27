package xyz.antgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.antgame.controller.PlayRoom;
import xyz.antgame.controller.UserInterface;
import xyz.antgame.domain.GameResultSet;

//    �����о�Ӧ�ò���Ҫ �û��ӿ�Ӧ����PlayRoom��ʵ��
//      ���뻹����Ҫ��playRoom���� �����߼��Ƚ����
//      ��Ҫ�����е���Ϸ�������ȫ���浽PlayRoom���� ���Һ�������Ҳ��������ƺ���ʵ

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
//        ��������

//        �������

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
