import { cancelMemberSession, getMemberPastSessions, getMemberUpcomingSessions } from "../api/memberApi";
import { useAuth } from "../contexts/AuthContext";
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { Navigate } from 'react-router-dom';
import MemberSessionsTable from "../components/MemberSessionsTable";

const MemberSessions = () => {
    const [upcomingSessions, setUpcomingSessions] = useState(null);
    const [pastSessions, setPastSessions] = useState(null);
    const {userId} = useAuth();

    useEffect(() => {
        getSessions();
        async function getSessions() {
            const upcomingSessionsResponse = await getMemberUpcomingSessions(userId);
            const pastSessionsResponse = await getMemberPastSessions(userId);
            setUpcomingSessions(upcomingSessionsResponse)
            setPastSessions(pastSessionsResponse)
        }
    }, []); 

    const handleCancelSessionClicked = (sessionId) => {
        cancelSession();
        async function cancelSession() {
            const deleteSessionResponse = await cancelMemberSession(sessionId);

            if (deleteSessionResponse === "Training session deleted") {
                const updatedUpcomingSessions = upcomingSessions.filter(session => session.sessionId !== sessionId);
                setUpcomingSessions(updatedUpcomingSessions);
            } else {
                console.error("Session not found or unsuccessful delete");
            }
        }
    }

    if (!userId) {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    return (
        <SessionsView>
            <MemberSessionsTable handleCancelSessionClicked={handleCancelSessionClicked} type={"Upcoming"} sessions={upcomingSessions}/>
            <MemberSessionsTable type={"Previous"} sessions={upcomingSessions}/>
        </SessionsView>
    )


}

export default MemberSessions;

const SessionsView = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
`