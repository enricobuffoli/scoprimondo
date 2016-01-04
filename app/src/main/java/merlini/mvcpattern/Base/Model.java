package merlini.mvcpattern.Base;

import java.util.Observable;

/**
 * Created by mattia on 26/11/15.
 */
public class Model extends Observable
{
    /**
     * Funzione che notifica a tutti gli observers registrati nel Model
     * (se compatibili con l'evento) lo scatenarsi di un evento
     */
    protected void fireValuesChange(Object data)
    {
        this.setChanged();
        this.notifyObservers(data);
    }

    protected void fireValuesChange()
    {
        this.fireValuesChange("Nessun evento specifico");
        this.debug();
    }

    protected void debug()
    {
        String out = String.format("Numero di observers: %d\n", this.countObservers());
        System.out.println(out);
    }
}
