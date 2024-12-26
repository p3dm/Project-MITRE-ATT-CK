package Mitre_ATTxCK;

public class MitreTechnique {
    private String techniqueId;
    private String name;
    private String description;
    private String tactic;

    public void setTactic(String tactic) {
        this.tactic = tactic;
    }
    public String getTactic() {
        return tactic;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setTechniqueId(String techniqueId){
        this.techniqueId = techniqueId;
    }
    public String getTechniqueId(){
        return techniqueId;
    }
}
