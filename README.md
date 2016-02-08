# scoprimondo
> APP Android

> :+1: Dev: @enricobuffoli @mattiamerlini

###ToDo
###### @enricobuffoli
- [ ] Dopo una rotazione di più di 180° e diverse specchiatura, la specchiatura rende inutilizzabile l'immagine. Problema del centro della simmetria.
- [X] Nel ButtonGestureView quando è selezionata un'immagine in un livello sottostante e viene applicata una trasforzazione (es: specchio) l'applicazione crasha.
- [X] Nel ButtonGestureView quando non c'è nessuna immagine scompare il canvas.
- [X] Nel ButtonGestureView, selezionando un'immagine PNG il canale alpha diventa tutto rosso.
- [X] ~~Controllare che non ci siano abusi di memoria nel `Bitmap.decodeResorces()`.~~ Succedono solo su SmartPhone, su Tablet tutto ok.
- [X] Sistemare le dimensioni del ButtonGestureView e dell'ImageMotionView in modo da avere un 30/70 di spazio percentuale.
- [X] Impostare le dimensioni dei pulsanti nel ButtonGestureView.
- [X] Modificare la classe ButtonGesture e ImageMotion per permetterne la replicazione (ne servono 5). Legare l'ImageMotion al ButtonGesture tramite codice, in modo da non dipendere staticamente dall'XML.
- [X] Predisporre un metodo toString() disponibile da ButtonGestureView per ricevere una descrizione di TUTTE le immagini presenti nella schermata. Questo dato sarà quello salvato a Database.
- [X] Implementare la funzione `private boolean isButtonGestureViewWellFormed(int tabIndex)` nella classe `app/src/main/java/it/mattiamerlini/mvc_scoprimondo/Views/TabHost/Impl/TabHostImpl.java`.

    La funzione è così fatta:

    ```java
private boolean isButtonGestureViewWellFormed(int tabIndex)
{
        ButtonGestureView buttonGesture = null;
        boolean returnValue = false;
        switch (tabIndex)
        {
            case 1: //MADRE NATURA
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
            case 2: //MADRE TERRA
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
            case 3: //TERRA PADRI
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
            case 4: //MADRE PATRIA
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
            case 5: //TERRA FRONTIERA
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
        }
        return returnValue;
}
    ```
    
    L'oggetto `buttonGesture` contiene il ButtonGestureView realtivo alla tab di cui si vuole salvare il contenuto a Database.
    Il commento `//Do checks` deve venir sostituito con delle condizioni che determinino se il ButtonGestureView in questione abbia il numero minimo necessario di elementi per passare alla tab successiva.
    Il booleano `returnValue` dovrà valere `true` se il check sul ButtonGesture è andato a buon fine, `false` altrimenti.
###### @mattiamerlini
- [X] Implementare la funzione `public static boolean requestButtonGestureViewSave(ButtonGestureView buttonGestureView, int index)` nella classe `app/src/main/java/it/mattiamerlini/mvc_scoprimondo/Utilities/RequestUtility.java`.

    La funzione statica è così fatta:
    ```java
    public static boolean requestButtonGestureViewSave(ButtonGestureView buttonGestureView, int index)
    {
        //Estrai dati dal button gesture
        String immagine = "testimmagine";
        String email = SessionUtility.getInstance(null).getUserLogged().getEmail();

        Map args = new HashMap();
        args.put("immagine", immagine);
        args.put("email", email);

        JSONEncodeAdapter encoder = new JSONEncodeAdapter();
        encoder.setAPICredential();
        encoder.setAction(RequestUtility.SAVE_ACTIONS[index]);
        encoder.setArgs(args);
        String query = encoder.getString();

        Request post = new Request(APIConfig.API_HOST, Request.CONTENT_TYPE_JSON);
        post.execute(query);
        String response = post.getResult();

        Console.log(String.format("Richiedo di salvare %d. Richiesta -> [%s] Risposta -> [%s]", index, query, response));

        JSONDecodeAdapter decoder = new JSONDecodeAdapter(response);

        if(decoder.getResponseCode() == 200)
            return true;
        return false;
    }
    ```
    
    Dopo il commento `//Estrai dati dal button gesture` va estratta dal ButtonGestureView la stringa descrittiva dell'immagine creata dall'utente.
- [X] Parametrizzare l'API in PHP creando un file di configurazione per la connessione al Database.
- [X] ~~Farsi dare le chiavi del server Scoprimondo e caricare l'API in PHP~~ API caricata su altro server.
- [X] Controllare che la connessione e i check fatti nella classe `NetworkUtility.java` funzionino correttamente sia con `hostname` o `indirizzo IP`.
- [X] Finire lo Spinner.
- [X] Creare l'Alert per lo Spinner.

###### @enricobuffoli + @mattiamerlini
- [X] ~~Inserire le giuste immagini nei 5 ButtonGestureView.~~ Dopo la verbalizzazione.
- [X] Test vari.
