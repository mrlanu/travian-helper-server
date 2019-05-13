package com.lanu.travian_helper.services;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.lanu.travian_helper.entities.Account;
import com.lanu.travian_helper.entities.Attack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BrowsingServiceImpl implements BrowsingService {

    @Autowired
    private WebClient webClient;

    HtmlPage currentPage;

    @Override
    public List<Account> getAllPlayers() {

        List<Account> accountList = new ArrayList<>();

        try {
            login( "Баба Яга", "28333555");

            HtmlAnchor statisticAnchor = currentPage.getAnchorByHref("statistiken.php");
            //Statistic Page
            currentPage = statisticAnchor.click();
            //get all available pages from paginator
            List<HtmlElement> paginator = currentPage.getByXPath("//div[@class='paginator']/a");
            //move to first page
            currentPage = paginator.get(0).click();
            HtmlTable table = currentPage.getHtmlElementById("player");

            int lastPageInPaginator = Integer.parseInt(paginator.get(paginator.size()-1).getAttribute("href").split("=")[2]);
            //System.out.println("Last Page is ====>>>> " + lastPageInPaginator);

            List<HtmlTableRow> tableRows = table.getRows();

            //get every player from table
            for (int j = 0; j < 3; j++){
                for (int i = 1; i < 21; i++){
                    List<HtmlTableCell> cellList = tableRows.get(i).getCells();
                    //select all a 0 - player 1 - alliance
                    List<HtmlAnchor> anchors = tableRows.get(i).getByXPath("td/a");
                    accountList.add(new Account(
                            Integer.parseInt(anchors.get(0).getAttribute("href").split("=")[1]),
                            accountList.size()+1,
                            cellList.get(1).asText(),
                            cellList.get(2).asText(),
                            Integer.parseInt(cellList.get(3).asText()),
                            Integer.parseInt(cellList.get(4).asText())));
                }
                HtmlAnchor nextPage = (HtmlAnchor) currentPage.getByXPath("//div[@class='paginator']/a[@class='next']").get(0);
                currentPage = nextPage.click();
                table = currentPage.getHtmlElementById("player");
                tableRows = table.getRows();
            }

            for (Account p : accountList){
                System.out.println(p);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    public List<Attack> injectAttackingAccountName(List<Attack> attacks){

        HtmlPage detailPage = null;

        if (currentPage == null){
            login( "Баба Яга", "28333555");
        }

        for (Attack attack : attacks){

            // inject details of attacking village
            try {
              detailPage = webClient.getPage(String.format("https://ts4.travian.ru/position_details.php?x=%d&y=%d", attack.getOffer().getX(), attack.getOffer().getY()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<HtmlAnchor> villageInfoTableAnchors = detailPage.getByXPath("//table[@id='village_info']//a");
            attack.getOffer().getPlayer().setName(villageInfoTableAnchors.get(1).asText());
            attack.getOffer().getPlayer().setId(Integer.parseInt(villageInfoTableAnchors.get(1).getAttribute("href").split("=")[1]));
            attack.getOffer().getPlayer().setAlliance(villageInfoTableAnchors.get(0).asText());
            attack.getOffer().setId(getVillageId(detailPage));

            // inject details of attacked village
            try {
                detailPage = webClient.getPage(String.format("https://ts4.travian.ru/position_details.php?x=%d&y=%d", attack.getDeffer().getX(), attack.getDeffer().getY()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            villageInfoTableAnchors = detailPage.getByXPath("//table[@id='village_info']//a");
            attack.getDeffer().getPlayer().setId(Integer.parseInt(villageInfoTableAnchors.get(1).getAttribute("href").split("=")[1]));
            attack.getDeffer().getPlayer().setAlliance(villageInfoTableAnchors.get(0).asText());
            attack.getDeffer().setId(getVillageId(detailPage));
        }

        webClient.close();

        return attacks;
    }

    private int getVillageId(HtmlPage page){
        HtmlAnchor anchorForVillageId = (HtmlAnchor) page.getByXPath("//div[@class='detailImage  ']//a").get(1);
        return Integer.parseInt(anchorForVillageId.getAttribute("href").split("=")[3]);
    }

    private void login(String user, String password){

        try {
            HtmlPage startPage = webClient.getPage("https://ts4.travian.ru/dorf1.php");
            System.out.println(startPage.getTitleText());

            HtmlForm loginForm = startPage.getFormByName("login");
            HtmlButton button = loginForm.getButtonByName("s1");
            HtmlTextInput textField = loginForm.getInputByName("name");
            HtmlPasswordInput textFieldPass = loginForm.getInputByName("password");
            textField.type(user);
            textFieldPass.type(password);

            //Village Page
            currentPage = button.click();

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public LocalDateTime getServerTime(){
        return LocalDateTime.now(ZoneId.of("Europe/Moscow"));

    }
}
