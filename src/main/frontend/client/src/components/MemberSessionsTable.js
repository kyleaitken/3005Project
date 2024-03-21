import React from 'react';
import styled from 'styled-components';

const MemberSessionsTable = (props) => {
    const {handleCancelSessionClicked, sessions, type} = props;


    const CancelSessionButton = ({sessionId}) => {
        const handleCancelSession = () => {
            handleCancelSessionClicked(sessionId)
        }
        
        return (
            <CancelButton onClick={handleCancelSession}>Cancel Session</CancelButton>
        )
    }

    const Header = ({type}) => {
        return (
        <HeaderView>
               { type === "Upcoming" ? <h1>Upcoming Sessions</h1> : <h1>Past Sessions</h1>}
        </HeaderView>
        )
    }

    return (
        <Container>
            {!sessions || sessions.length === 0 ? (
                <h3>No {type} Sessions</h3>
            ) : (
                <>
                <Header type={type}/>
                <Table>
                    <thead>
                        <tr>
                            <TableHeader>Date</TableHeader>
                            <TableHeader>Time</TableHeader>
                            <TableHeader>Trainer</TableHeader>
                            <TableHeader />
                        </tr>
                    </thead>
                    <tbody>
                        {sessions && sessions.map(item => (
                            <TableRow key={item.sessionId}>
                                <TableCell>{item.date}</TableCell>
                                <TableCell>{item.time}</TableCell>
                                <TableCell>{item.trainerName}</TableCell>
                                <TableCell>{type === "Upcoming" ? <CancelSessionButton sessionId={item.sessionId}/> :  <DummyView/>}</TableCell>
                            </TableRow>
                        ))}
                    </tbody>
                </Table>
                 </>
            )}
        </Container>
    );
}

export default MemberSessionsTable;

const Container = styled.div`
    margin-top: 20px;
`;

const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
`;

const TableHeader = styled.th`
    padding: 10px 200px 10px 0px;
    text-align: center;
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
    text-align: center;
    font-size: 20px;
`;

const CancelSessionButton = styled.div``

const CancelButton = styled.button`
    padding: 10px 20px;
    background-color: red; 
    width: 170px;
    align-self: flex-end;
    color: white;
    border: none;
    cursor: pointer;
    font-size: 16px;
    border-radius: 5px;

    &:hover {
        background-color: orange; 
    }
`

const DummyView = styled.div`
    width: 170px;
`

const HeaderView = styled.div`
    margin-left: 50px;
    margin-bottom: 50px;
`