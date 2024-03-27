import React, { useState, useEffect, useCallback } from "react";
import styled from "styled-components";
import { useAuth } from "../contexts/AuthContext";
import { Navigate } from 'react-router-dom';
import { getEquipment, repairEquipment, reportEquipmentBroken } from "../api/adminApi";

const EquipmentContainer = () => {
    const [equipment, setEquipment] = useState([]);
    const { userId, userType } = useAuth();

    const loadEquipment = useCallback(async (type) => {
        if (!userType === "Admin") return;
        let fetchedEquipment = await getEquipment();
        setEquipment(fetchedEquipment);
    }, [userType]);


    useEffect(() => {
        loadEquipment();
    }, [loadEquipment]);

    const tryRepairEquipment = useCallback(async (equipmentId) => {
        try {
            const res = await repairEquipment(equipmentId);
            if (res === "Success") {
                loadEquipment();
            } else {
                window.alert("Unable to repair equipment");
            }
        } catch {
            window.alert("Unable to repair equipment");
        }
    }, [loadEquipment])


    const trySetEquipmentBroken = useCallback(async (equipmentId) => {
        try {
            const res = await reportEquipmentBroken(equipmentId);
            if (res === "Success") {
                loadEquipment();
            } else {
                window.alert("Unable to set equipment broken");
            }
        } catch {
            window.alert("Unable to repair equipment broken");
        }
    }, [loadEquipment])

    if (!userId || userType !== "Admin") {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    return (
        <Container>
            <Header>Equipment</Header>
            <ColumnHeaders>
                <ColumnHeader>
                    Name
                </ColumnHeader>
                <ColumnHeader>
                    Status
                </ColumnHeader>
                <ColumnHeader>
                </ColumnHeader>
                <ColumnHeader>
                </ColumnHeader>
            </ColumnHeaders>
            <EquipmentList>
                {equipment.map((item, index) => (
                    <EquipmentRow key={item.equipmentId}>
                        <EquipmentItem >
                            <div>{item.equipmentName}</div>
                        </EquipmentItem>
                        <EquipmentItem>
                            {item.needsRepair ? <div>Needs Repair</div> : <div>Working</div>}
                        </EquipmentItem>
                        <EquipmentItem>
                        {item.needsRepair  && 
                        <RepairButton onClick={() => tryRepairEquipment(item.equipmentId)}>Set Repaired</RepairButton>}
                        </EquipmentItem>
                        <EquipmentItem>
                        {!item.needsRepair  && 
                        <ReportButton onClick={() => trySetEquipmentBroken(item.equipmentId)}>Set Broken</ReportButton>}
                        </EquipmentItem>
                    </EquipmentRow>
                ))}
            </EquipmentList>
        </Container>
    );
};

export default EquipmentContainer;

// Styled components
const Container = styled.div``;


const Header = styled.h2`
    margin-left: 40px;
`;

const EquipmentList = styled.div``;

const EquipmentItem = styled.div`
    width: 200px;

`;

const EquipmentRow = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin: 20px 20px;
    text-align: center;
    font-size: 16px;

`;

const RepairButton = styled.button`
    padding: 5px 10px;
    background-color: green; 
    width: 140px;
    align-self: flex-end;
    color: white;
    border: none;
    cursor: pointer;
    font-size: 14px;
    border-radius: 5px;

    &:hover {
        background-color: orange; 
    }
`;


const ReportButton = styled.button`
    padding: 5px 10px;
    background-color: darkred; 
    width: 140px;
    align-self: flex-end;
    color: white;
    border: none;
    cursor: pointer;
    font-size: 14px;
    border-radius: 5px;

    &:hover {
        background-color: orange; 
    }
`;

const ColumnHeader = styled.div`
    font-weight: bold;
    font-size: 18px;
    width: 200px;
    text-align: center;
`;

const ColumnHeaders = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin: 20px 20px;
`