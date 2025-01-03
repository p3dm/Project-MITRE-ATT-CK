package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MitreTechnique {
    private String techniqueId;
    private String name;
    private String description;
    private String attackTechnique;
    private String externalId;

    public String getAttackTechnique() {
        return attackTechnique;
    }
    public void setAttackTechnique(String attackTechnique) {
        this.attackTechnique = attackTechnique;
    }

    public String getId() {
        return techniqueId;
    }

    public void setId(String techniqueId) {
        this.techniqueId = techniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalId(){
        return externalId;
    }
    public String setExternal_id(String external_id){
        return this.externalId = external_id;
    }
}
