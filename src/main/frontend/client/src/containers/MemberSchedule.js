import React, { useEffect, useState } from 'react';
import { getMemberSchedule } from '../api/memberApi';
import { useAuth } from '../contexts/AuthContext';
import styled from 'styled-components';
import ScheduleTable from '../components/ScheduleTable';


const MemberSchedule = () => {
    const [schedule, setSchedule] = useState(null);
    const {userId} = useAuth();

    useEffect(() => {
        getSchedule();
        async function getSchedule() {
            console.log('fetching schedule')
            const scheduleResponse = await getMemberSchedule(userId);
            console.log(scheduleResponse);
            setSchedule(scheduleResponse)
        }
    }, [userId]); 

    return (
        <MemberScheduleView>
            <ScheduleTable schedule={schedule} />
        </MemberScheduleView>
    )

}

export default MemberSchedule;

const MemberScheduleView = styled.div`
    diaply: flex;
    margin-left: 40px;
`