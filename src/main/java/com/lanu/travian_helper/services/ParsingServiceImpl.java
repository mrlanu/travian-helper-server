package com.lanu.travian_helper.services;

import com.lanu.travian_helper.entities.Attack;
import com.lanu.travian_helper.models.AttacksString;
import com.lanu.travian_helper.entities.Player;
import com.lanu.travian_helper.entities.Village;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ParsingServiceImpl implements ParsingService {

    @Autowired
    private BrowsingService browsingService;

    @AllArgsConstructor
    private class Coordinates{
        private int x;
        private int y;
    }

    private List<String> stringsList;
    private List<String> allAttacks = new ArrayList<>();

    @Override
    public List<Attack> getAllAttacks(AttacksString attacksString) {

        List<Attack> result = new ArrayList<>();

        init(attacksString);

        for (int i = 0; i < allAttacks.size(); i+=4){
            result.add(new Attack(
                                null,
                                // Offer
                                new Village(0,
                                    getAttackingVillageName(i),
                                    getCoordinatesAttackingVillage(i).x,
                                    getCoordinatesAttackingVillage(i).y,
                                    new Player(0, getPlayerName(), null)),
                                // Deffer
                                new Village(0,
                                        getAttackedVillageName(),
                                        getCoordinatesAttackedVillage(getAttackedVillageName()).x,
                                        getCoordinatesAttackedVillage(getAttackedVillageName()).y,
                                        new Player(0, getPlayerName(), null)),
                                getAttackTime(i), 1));
        }

        return browsingService.injectAttackingAccountName(result);
    }

    private void init(AttacksString attacksString){
        allAttacks.clear();

        String[] stringsArray = attacksString.getText().split("\n");
        stringsList = Arrays.asList(stringsArray);

        splitStringForAllAttacks();
    }

    private void splitStringForAllAttacks(){
        for (int i = 0; i < stringsList.size() - 1; i++) {
            String s = stringsList.get(i);

            if (s.contains("Прибывающие войска")) {

                s = s.substring(20);
                int resInt = Integer.parseInt(s.split("\\)")[0]);

                for (int j = i + 1; j < i + 1 + resInt * 4; j+=4){

                    String s2 = stringsList.get(j);
                    if (s2.contains("нападение")){
                        for (int k = j; k < j + 4; k++){
                            allAttacks.add(stringsList.get(k));
                        }
                    }
                }
                break;
            }
        }
    }

    private String getPlayerName(){
        String hero = "";

        for (int i = 0; i < stringsList.size() - 1; i++){
            String s = stringsList.get(i);
            if (s.contains("Герой")){
                hero = s.split("  ")[1];
                break;
            }
        }
        return hero;
    }

    private String getAttackedVillageName(){
        String attackedVillage = allAttacks.get(0).split("\t")[1];
        String[] attackedVillageArr = attackedVillage.split("на");
        return attackedVillageArr[attackedVillageArr.length - 1].trim();
    }

    private Coordinates getCoordinatesAttackedVillage(String attackedVillage){

        int attackedVillageX = 0;
        int attackedVillageY = 0;

        for (int i = stringsList.size()-1; i >= 0; i--){
            String s = stringsList.get(i);
            if (s.equals(attackedVillage)){
                String cord = stringsList.get(i + 1);
                attackedVillageX = Integer.parseInt(cord.split("\\|")[0]
                        .replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "")
                        .replaceAll("\u2212", "-").substring(1));
                attackedVillageY = Integer.parseInt(cord.split("\\|")[1]
                        .replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "")
                        .replaceAll("\u2212", "-").split("\\)")[0]);
                break;
            }
        }
        return new Coordinates(attackedVillageX, attackedVillageY);
    }

    private String getAttackingVillageName(int startParsingFrom){
        return allAttacks.get(startParsingFrom).split("\t")[0];
    }

    private Coordinates getCoordinatesAttackingVillage(int startParsingFrom){

        int attackingVillageX = Integer.parseInt(allAttacks.get(startParsingFrom + 1).split("\\|")[0].substring(2)
                .replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "")
                .replaceAll("\u2212", "-"));
        int attackingVillageY = Integer.parseInt(allAttacks.get(startParsingFrom + 1).split("\\|")[1].split("\\)")[0]
                .replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "")
                .replaceAll("\u2212", "-"));

        return new Coordinates(attackingVillageX, attackingVillageY);
    }

    private LocalTime getAttackDuration(int startParsingFrom){
        String[] durationArr = allAttacks.get(startParsingFrom + 3).split(" ")[1].split(":");
        return LocalTime.of(Integer.parseInt(durationArr[0]), Integer.parseInt(durationArr[1]), Integer.parseInt(durationArr[2]));
    }

    private LocalTime getAttackTime(int startParsingFrom){
        String[] attackTime = allAttacks.get(startParsingFrom + 3).split(" ")[3].split(":");
        return LocalTime.of(Integer.parseInt(attackTime[0]), Integer.parseInt(attackTime[1]), Integer.parseInt(attackTime[2]));
    }
}
