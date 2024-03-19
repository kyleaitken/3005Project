package com.example.project.Models;

public class Equipment {
    private Integer equipmentId;
    private String equipmentName;
    private boolean needsRepair;

    public Equipment(){}

    public Equipment(Integer id, String equipmentName, boolean needsRepair) {
        this.equipmentId = id;
        this.equipmentName = equipmentName;
        this.needsRepair = needsRepair;
    }

    public Integer getEquipmentId() {return equipmentId;}
    public String getEquipmentName() {return equipmentName;}
    public boolean getNeedsRepair() {return needsRepair;}

    public void setEquipmentName(String name) { this.equipmentName = name;}
    public void setNeedsRepair(boolean needsRepair) { this.needsRepair = needsRepair;}
    public void setEquipmentId(Integer id) {this.equipmentId = id;}


}
