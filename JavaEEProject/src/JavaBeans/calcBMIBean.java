package JavaBeans;

import javax.ejb.Stateless;

@Stateless(name = "calcBMIEJB")
public class calcBMIBean {
    public calcBMIBean() { }

    public float calcBMI (float weight, float height) {
        return (weight / (height * height));
    }
}
