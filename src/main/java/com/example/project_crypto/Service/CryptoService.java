package com.example.project_crypto.Service;

import com.example.project_crypto.Model.Crypto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class CryptoService {
    private ArrayList<Crypto> cryptosList;

    // Sorting by names..
    public void sortCryptosByName() {
        cryptosList.sort(Comparator.comparing(Crypto::getName));
    }

    // Sorting by quantity..
    public void sortCryptosByQuantity() {
        cryptosList.sort(Comparator.comparing(Crypto::getQuantity));
    }

    // Sorting by price..
    public void sortCryptosByPrice() {
        cryptosList.sort(Comparator.comparing(Crypto::getPrice));
    }

    // Adds crypto to portfolio
    public String addCrypto(Crypto crypto){
        if (cryptosList.add(crypto)) {return "Crypto added.";}
        else {return "Crypto failed to add.";}
    }

    // Writes down raw data output in the form of string
    public String getInfoJSON(Crypto crypto) {
        return "{" + "\r\n"   +
                  "id: "      + crypto.getId()       + "\r\n" +
                  "name:"     + crypto.getName()     + "\r\n" +
                  "symbol:"   + crypto.getSymbol()   + "\r\n" +
                  "price:"    + crypto.getPrice()    + "\r\n" +
                  "quantity:" + crypto.getQuantity() + "\r\n" +
                "}";
    }

    public Crypto getCryptoByID(int id){
        Crypto xc = null;
        for (Crypto c: cryptosList) {
            if (c.getId() == id) {
                xc = c;
                break;
            }
        }
        return xc;
    }

    public String updateCrypto(int id, Crypto newCrypto){
        Crypto xc = getCryptoByID(id);
        if (xc != null) {
           xc.setId(newCrypto.getId());
           xc.setName(newCrypto.getName());
           xc.setPrice(newCrypto.getPrice());
           xc.setQuantity(newCrypto.getQuantity());
           xc.setSymbol(newCrypto.getSymbol());
           return "Crypto updated";
        } else {
          return  "Crypto not found.";
        }
    }

    public String getCryptoInfo(Integer id) {
        Crypto xc = getCryptoByID(id);
        if (xc != null) {
            return getInfoJSON(xc);
        } else {
            return  "Crypto not found.";
        }
    }

    // Writes down raw data output of all objects in the form of string
    public String getAllInfo() {
        String res = "[" + "\r\n";
        for (int i = 0; i < cryptosList.size(); i++)
        {
            if (i >= 1) {
                res = res.concat(",");
            }
            res = res.concat(getInfoJSON(cryptosList.get(i)));
        }
        res = res +  "\r\n" + "]" ;

        return res;
    }

    public String getPortfolioValue(){
        double total = 0;
        for (Crypto c: cryptosList){
          total = total + (c.getQuantity() * c.getPrice());
        }
        return "Total value of portfolio is : " + total;
    }

    public CryptoService() {
        this.cryptosList = new ArrayList<>();
    }

    public ArrayList<Crypto> getCryptosList() {
        return cryptosList;
    }

    public void setCryptosList(ArrayList<Crypto> cryptosList) {
        this.cryptosList = cryptosList;
    }
}
