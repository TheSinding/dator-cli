package main.java.utils;

/**
 * Created by Simon Sinding - Ren Remoulade on 4/4/17.
 */
public enum Names{
    PRODUCTID("Product ID")
    , NAME("Name")
    , CURRENTSTOCK("Current Stock")
    , PREFEREDSTOCK("Prefered Stock")
    , MINIMUMSTOCK("Minimum Stock")
    , BUSSPEED("Bus Speed")
    , TYPE("Type")
    , KIND("Kind")
    , FORMFACTOR("Form Factor")
    , SOCKET("Socket")
    , PRICE("Price")
    , GRAPHICSCARD("Graphics Card")
    , CPU("CPU")
    , RAM("RAM")
    , MOTHERBOARD("Motherboard")
    , CASE("SystemCase")
    , CATEGORY("Category")
    , BOLDTEXT("\033[0;1m");

    private String name;

    Names(String s) {
        this.name = s;
    }
    @Override
    public String toString() {
        return name;
    }
}
