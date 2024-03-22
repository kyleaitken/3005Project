import React from 'react';
import styled from 'styled-components';

const ScheduleTable = ({ schedule }) => {
    return (
        <Container>
            {!schedule || schedule.length === 0 ? (
                <h1>Your schedule is empty</h1>
            ) : (
                <>
                    <h1> Your Schedule </h1>
                <Table>
                    <thead>
                        <tr>
                            <TableHeader>Date</TableHeader>
                            <TableHeader>Time</TableHeader>
                            <TableHeader>Type</TableHeader>
                            <TableHeader>Trainer</TableHeader>
                        </tr>
                    </thead>
                    <tbody>
                        {schedule && schedule.map(item => (
                            <TableRow key={item.session_id}>
                                <TableCell>{item.date}</TableCell>
                                <TableCell>{item.time}</TableCell>
                                <TableCell>{item.className ? 'Class' : 'Training Session'}</TableCell>
                                <TableCell>{item.trainerName}</TableCell>
                            </TableRow>
                        ))}
                    </tbody>
                </Table>
                </>
            )}
        </Container>
    );
}

export default ScheduleTable;

const Container = styled.div`
    margin-top: 20px;
`;

const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
`;

const TableHeader = styled.th`
    padding: 10px 200px 10px 0px;
    text-align: left;
    border-bottom: 1px solid #ddd;
    font-size: 22px;
`;

const TableRow = styled.tr`
    &:nth-child(even) {
        background-color: #f2f2f2;
    }
`;

const TableCell = styled.td`
    padding: 20px 200px 20px 0px;
    border-bottom: 1px solid #ddd;
`;
