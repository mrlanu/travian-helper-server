package com.lanu.travian_helper.services;

import com.lanu.travian_helper.models.Attack;
import com.lanu.travian_helper.models.AttacksString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ParsingServiceImpl implements ParsingService {

    @Override
    public List<Attack> getAllAttacks(AttacksString attacksString) {
        List<String> result = new ArrayList<>();
        String[] stringsArray = attacksString.getText().split("\n");
        List<String> stringsList = Arrays.asList(stringsArray);

        for (int i = 0; i < stringsList.size() - 1; i++) {
            String s = stringsList.get(i);
            if (s.contains("Прибывающие войска")) {

                s = s.substring(20);
                int resInt = Integer.parseInt(s.substring(0,1));

                for (int j = i + 1; j < i + 1 + resInt * 4; j+=4){

                    String s2 = stringsList.get(j);
                    if (s2.contains("нападение")){
                        for (int k = j; k < j + 4; k++){
                            result.add(stringsList.get(k));
                        }
                    }
                }
                break;
            }
        }

        return null;
    }
}
