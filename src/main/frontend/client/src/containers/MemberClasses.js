import { leaveClass, getMemberPastClasses, getMemberUpcomingClasses } from "../api/memberApi";
import { useAuth } from "../contexts/AuthContext";
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { Navigate } from 'react-router-dom';
import MemberClassesTable from "../components/MemberClassesTable";

const MemberClasses = () => {
    const [upcomingClasses, setUpcomingClasses] = useState(null);
    const [pastClasses, setPastClasses] = useState(null);
    const {userId} = useAuth();

    useEffect(() => {
        getClasses();
        async function getClasses() {
            const upcomingClassesResponse = await getMemberUpcomingClasses(userId);
            const pastClassesResponse = await getMemberPastClasses(userId);
            setUpcomingClasses(upcomingClassesResponse)
            setPastClasses(pastClassesResponse)
        }
    }, []); 

    const handleLeaveClassClicked = async (classId) => {
        try {
            const response = await leaveClass(userId, classId);            
            console.log('res', response)
    
            if (response === "Success") {
                const updatedUpcomingClasses = upcomingClasses.filter(fitnessClass => fitnessClass.classId !== classId);
                setUpcomingClasses(updatedUpcomingClasses);
            } else {
                console.error("Class not found or unsuccessful delete:", response);
            }
        } catch (error) {
            console.error("Failed to leave class:", error);
        }
    };

    if (!userId) {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    return (
        <ClassesView>
            <MemberClassesTable buttonHandler={handleLeaveClassClicked} type={"Upcoming"} classes={upcomingClasses}/>
            <MemberClassesTable type={"Previous"} classes={pastClasses}/>
        </ClassesView>
    )
}

export default MemberClasses;

const ClassesView = styled.div`
    display: flex;
    flex-direction: column;
    flex-grow: 1;
`