import { cancelMemberSession, getMemberPastSessions, getMemberUpcomingSessions, getAvailableTrainers, addSession } from "../api/memberApi";
import { useAuth } from "../contexts/AuthContext";
import React, { useEffect, useState, useCallback } from 'react';
import styled from 'styled-components';
import { Navigate } from 'react-router-dom';
import MemberSessionsTable from "../components/MemberSessionsTable";
import ScheduleSessionForm from "../components/ScheduleSessionForm";

const MemberSessions = () => {
    const [upcomingSessions, setUpcomingSessions] = useState(null);
    const [pastSessions, setPastSessions] = useState(null);
    const [trainers, setTrainers] = useState(null);
    const {userId} = useAuth();

    const getSessions = useCallback(async () => {
        if (userId) {
            const upcomingSessionsResponse = await getMemberUpcomingSessions(userId);
            const pastSessionsResponse = await getMemberPastSessions(userId);
            setUpcomingSessions(upcomingSessionsResponse);
            setPastSessions(pastSessionsResponse);
        }
    }, [userId]);

    const fetchTrainers = useCallback(async () => {
        const trainersResponse = await getAvailableTrainers();
        setTrainers(trainersResponse);
    }, []); 

    useEffect(() => {
        getSessions();
        fetchTrainers();
    }, [getSessions, fetchTrainers]); 

    const handleCancelSessionClicked = useCallback(async (sessionId) => {
        try {
            const response = await cancelMemberSession(sessionId);
    
            if (response === "Training session deleted") {
                const updatedUpcomingSessions = upcomingSessions.filter(session => session.sessionId !== sessionId);
                setUpcomingSessions(updatedUpcomingSessions);
            } else {
                window.alert("Unable to delete session")
            }
        } catch (error) {
            console.error("Failed to cancel session:", error);
            window.alert("Unable to delete session")
        }
    });

    const handleAddSession = useCallback(async (date, time, trainerId) => {
        try {
            const response = await addSession(userId, trainerId, date, time)
            if (response.message == "Success") {
                getSessions();
            } else {
                window.alert("Unable to add session")
            }
        } catch (error) {
            console.error("Failed to add session:", error);
            window.alert("Unable to add session")
        }
    });

    if (!userId) {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    return (
        <SessionsView>
            <ScheduleSessionForm handleAddSession={handleAddSession} trainers={trainers}/>
            <MemberSessionsTable handleCancelSessionClicked={handleCancelSessionClicked} type={"Upcoming"} sessions={upcomingSessions}/>
            <MemberSessionsTable type={"Previous"} sessions={pastSessions}/>
        </SessionsView>
    )


}

export default MemberSessions;

const SessionsView = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
`