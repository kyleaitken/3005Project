import React, { useState, useEffect, useCallback } from "react";
import styled from "styled-components";
import { useAuth } from "../contexts/AuthContext";
import { Navigate } from 'react-router-dom';
import { getTrainer, getUpcomingSessions, getPastSessions, deleteSession, updateSchedule } from "../api/trainerApi";

const TrainerSchedule = () => {
    const [currentType, setCurrentType] = useState("upcoming"); 
    const [sessions, setSessions] = useState([]);
    const [trainer, setTrainer] = useState('');
    const [startTime, setStartTime] = useState('');
    const [endTime, setEndTime] = useState('');
    const { userId, userType } = useAuth();

    const noSessions = !sessions || sessions.length === 0;

    const loadSessions = useCallback(async (type) => {
        if (!userType === "Trainer") return;

        let fetchedSessions = [];
        switch (type) {
            case "upcoming":
                fetchedSessions = await getUpcomingSessions(userId);
                break;
            case "previous":
                fetchedSessions = await getPastSessions(userId);
                break;
            default:
                fetchedSessions = [];
        }
        setSessions(fetchedSessions);
    }, [userType]);

    const changeSessionsType = (type) => {
        setCurrentType(type);
        loadSessions(type);
    };

    const fetchTrainer = async () => {
        if (userType !== "Trainer") return;
        const res = await getTrainer(userId);
        setTrainer(res);
        console.log(res);
        setStartTime(res.startTime);
        setEndTime(res.endTime);
    }

    useEffect(() => {
        fetchTrainer();
        loadSessions(currentType);
    }, [currentType, loadSessions, getTrainer]);


    const tryDeleteSession = useCallback(async (sessionId) => {
        console.log('delete session: ', sessionId)
        try {
            const res = await deleteSession(sessionId);
            if (res === "Success") {
                loadSessions(currentType);
            } else {
                window.alert("Unable to delete session");
            }
        } catch {
            window.alert("Unable to delete session");
        }
    }, [currentType, loadSessions])


    const handleSubmit = async (event) => {
        event.preventDefault();
        const userConfirmed = window.confirm("Updating your start and end time will automatically cancel sessions outside of these times. Do you wish to proceed?");
        if (userConfirmed) {
            console.log('startTime', startTime, 'endTime', endTime);
            const scheduleUpdateRequest = {
                startTime,
                endTime
            };
            try {
                const res = await updateSchedule(userId, scheduleUpdateRequest);
                if (res.message === "Success") {
                    window.alert("Schedule updated");
                } else {
                    window.alert("Unable to update schedule")
                }
            } catch {
                window.alert("Unable to update schedule")
            }

        } else {
            console.log("Update canceled by the user.");
        }
        loadSessions(currentType);
        fetchTrainer();
    };

    if (!userId || userType !== "Trainer") {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    return (
        <MainContainer>
            <SessionsContainer>
                <TabBar>
                    <Tab onClick={() => changeSessionsType("upcoming")}>Upcoming Sessions</Tab>
                    <Tab onClick={() => changeSessionsType("previous")}>Previous Sessions</Tab>
                </TabBar>
                <Header>{currentType === "upcoming" ? <div>Upcoming Sessions</div> : <div>Past Sessions</div>}</Header>
                <ColumnHeaders>
                    <ColumnHeader>
                        Date
                    </ColumnHeader>
                    <ColumnHeader>
                        Time
                    </ColumnHeader>
                    <ColumnHeader>
                        Member
                    </ColumnHeader>
                    <ColumnHeader>
                    </ColumnHeader>
                </ColumnHeaders>
                {!noSessions && <SessionList>
                    {sessions.map((session, index) => (
                        <SessionRow key={session.sessionId}>
                            <SessionItem >
                                <div>{session.date}</div>
                            </SessionItem>
                            <SessionItem>
                                <div>{session.time}</div>
                            </SessionItem>
                            <SessionItem>
                                <div>{session.memberFirstName} {session.memberLastName}</div>
                            </SessionItem>
                            <SessionItem>
                            {currentType === "upcoming" && 
                            <CancelSessionButton onClick={() => tryDeleteSession(session.sessionId)}>Cancel Session</CancelSessionButton>}
                            </SessionItem>
                        </SessionRow>
                    ))}
                </SessionList>}
            </SessionsContainer>
            {startTime && endTime && <FormView>
            <h1>Update Schedule</h1>
            <FormContainer onSubmit={handleSubmit}>
                <Label>Start Time:
                    <Select 
                        value={startTime} 
                        onChange={(e) => setStartTime(e.target.value)} 
                    >
                        {Array.from({ length: 11 }, (_, i) => 7 + i).map(time => (
                        <option key={time} value={time}>{`${time}:00`}</option>
                        ))}
                    </Select>
                </Label>
                <Label>End Time:
                    <Select 
                        value={endTime} 
                        onChange={(e) => setEndTime(e.target.value)}
                    >
                        {Array.from({ length: 11 }, (_, i) => 8 + i).map(time => (
                        <option key={time} value={time}>{`${time}:00`}</option>
                        ))}
                    </Select>
                </Label>
                <SubmitButton type="submit">Update Schedule</SubmitButton>
            </FormContainer>
            </FormView>}
        </MainContainer>
    );
};

export default TrainerSchedule;

// Styled components
const MainContainer = styled.div`
    display: flex;
    flex-direction: row;
`

const SessionsContainer = styled.div``;

const TabBar = styled.div``;

const Tab = styled.button`
    padding: 10px 10px;
    background-color: #ACACAC; 
    width: 300px;
    align-self: flex-end;
    color: white;
    cursor: pointer;
    font-size: 18px;

    &:hover {
        background-color: #666666; 
    }
`;

const Header = styled.h2`
    margin-left: 40px;
`;

const SessionList = styled.div``;

const SessionItem = styled.div`
    width: 200px;

`;

const SessionRow = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin: 20px 20px;
    text-align: center;
    font-size: 16px;

`;

const CancelSessionButton = styled.button`
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

const FormContainer = styled.form`
    display: flex;
    align-items: center;
    gap: 5px;
    margin-bottom: 20px;
`;

const Label = styled.label`
    display: flex;
    margin: 0px 50px 0px 50px;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    font-size: 16px;
`;

const Select = styled.select`
    margin-top: 5px;
    font-size: 16px;
`;

const SubmitButton = styled.button`
    padding: 10px 20px;
    margin-left: 40px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;

    &:hover {
        background-color: #0056b3;
    }
`;

const FormView = styled.div`
    display: flex:
    flex-direction: column;
`