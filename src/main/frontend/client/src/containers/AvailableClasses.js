import { getMemberAvailableClasses, joinClass } from "../api/memberApi";
import { useAuth } from "../contexts/AuthContext";
import React, { useEffect, useState, useCallback } from 'react';
import styled from 'styled-components';
import { Navigate } from 'react-router-dom';
import MemberClassesTable from "../components/MemberClassesTable";

const AvailableClasses = () => {
    const [availableClasses, setAvailableClasses] = useState(null);
    const {userId} = useAuth();

    const getAvailableClasses = useCallback(async () => {
        if (userId) {
            const availableClassesResponse = await getMemberAvailableClasses(userId);
            setAvailableClasses(availableClassesResponse)
        }
    })

    useEffect(() => {
        getAvailableClasses();
    }, []); 

    const handleJoinClassClicked = useCallback(async (classId) => {
        try {
            const response = await joinClass(userId, classId);            
            console.log('res', response)
            if (response.message === "Success") {
                const updatedAvailableClasses = availableClasses.filter(fitnessClass => fitnessClass.classId !== classId);
                setAvailableClasses(updatedAvailableClasses);
            } else {
                console.error("Error: ", response.message);
                window.alert("Unable to join class due to schedule conflict")
            }
        } catch (error) {
            console.error("Failed to add member to class:", error);
        }
    });

    if (!userId) {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    return (
        <ClassesView>
            <MemberClassesTable buttonHandler={handleJoinClassClicked} type={"Available"} classes={availableClasses}/>
        </ClassesView>
    )
}

export default AvailableClasses;

const ClassesView = styled.div`
    display: flex;
    flex-grow: 1;
    flex-direction: column;
`