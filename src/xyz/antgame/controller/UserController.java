package xyz.antgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.antgame.service.PlayRoom;
import xyz.antgame.service.UserInterface;
import xyz.antgame.domain.GameResultSet;
import xyz.antgame.domain.UserRequest;

//    �����о�Ӧ�ò���Ҫ �û��ӿ�Ӧ����PlayRoom��ʵ��
//      ���뻹����Ҫ��playRoom���� �����߼��Ƚ����
//      ��Ҫ�����е���Ϸ�������ȫ���浽PlayRoom���� ���Һ�������Ҳ��������ƺ���ʵ


//      ע��ǰ�˴�ֵ��ʱ�� �������ּ��ÿո�ָ�
//      ���ǰ��˲�֪����ô������ �����������
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
//        ����û���Ҫ��Ҫ�Ѵ����������ݵĴ�����ȡ��ȥ��ɷ���
//        ��ȡ��ȥ�Ļ� ��һЩ��ֱ�� Ҳ�п��ܲ�̫�����߼�
//        ����ȡ��ȥ�����������
        if(userRequest.getAntPositions()!=null){
//        ��������
            antNum = userRequest.getAntNum();
            incTime = userRequest.getIncTime();
//        �������
            antDirections = new int[antNum];
            antPositions = new int[antNum];
            //        ��������
            String[] tempString = userRequest.getAntDirections().split(" ");
            for (i = 0; i < antNum; i++) {
                antDirections[i] = Integer.parseInt(tempString[i]);
            }
            tempString = userRequest.getAntPositions().split(" ");
            for (i = 0; i < antNum; i++) {
                antPositions[i] = Integer.parseInt(tempString[i]);
            }
            poleLength = userRequest.getPoleLength();
//        �������
        }else{
            antNum = 5;
            incTime = 1;
            antPositions = new int[]{30,60,90,120,150};
            antDirections = new int[]{-1,1,-1,1,-1};
            poleLength = 200;
        }
//        ΪplayRoom����ע������ֵ
        playRoom.setIncTime(incTime);
        playRoom.setAntNum(antNum);
//        �Զ�����Ϸ���е�����
        playRoom.setAntDirections(antDirections);
        playRoom.setAntPositions(antPositions);
//        ����
        playRoom.setPoleLength(poleLength);
//        ע�����

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
//        �������� ���ڶ��δ�ֵʱ ��ʱ��Ϊǰ�˻����Щֵһ�𴫹��� ���ǲ��ܸ���
//        ��û�в�����ʱ�� ���ڰ󶨲����Ķ���������û������ �����Ǹ����������Ǵ����˵�
        if (userRequest.getAntPositions() != null) {
            antNum = userRequest.getAntNum();
            incTime = userRequest.getIncTime();
//        �������
            antPositions = new int[antNum];
            //        ��������
            String[] tempString;
            tempString = userRequest.getAntPositions().split(" ");
            for (i = 0; i < antNum; i++) {
                antPositions[i] = Integer.parseInt(tempString[i]);
            }
            poleLength = userRequest.getPoleLength();
//        �������
        } else {
            antNum = 5;
            incTime = 1;
            antPositions = new int[]{30,60,90,120,150};
            poleLength = 200;
        }
//        ΪplayRoom����ע������ֵ ��Щֵ�ڵڶ��ε���֮�󶼲���ı�
        playRoom.setIncTime(incTime);
        playRoom.setAntPositions(antPositions);
        playRoom.setAntNum(antNum);
        playRoom.setPoleLength(poleLength);
//        ע�����

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
