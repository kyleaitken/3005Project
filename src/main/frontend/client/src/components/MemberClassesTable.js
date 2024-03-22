import React from 'react';
import styled from 'styled-components';

const MemberClassesTable = (props) => {
    const {handleLeaveClassClicked, classes, type} = props;
    const noClasses = !classes || classes.length === 0;

    const LeaveClassButton = ({ classId }) => (
        <LeaveButton onClick={() => handleLeaveClassClicked(classId)}>
            Leave Class
        </LeaveButton>
    );

    const Header = ({type}) => {
        return (
            <HeaderView>
                    {noClasses ?  (<h1>No {type} Classes</h1>) : (<h1>{type} Classes</h1>)}
            </HeaderView>
            )
    }

    return (
        <Container>
            {noClasses ? (
                <Header type={type}/> 
            ) : (
                <>
                <Header type={type}/>
                <Table>
                    <thead>
                        <tr>
                            <TableHeader>Class</TableHeader>
                            <TableHeader>Date</TableHeader>
                            <TableHeader>Time</TableHeader>
                            <TableHeader>Trainer</TableHeader>
                            <TableHeader>Room</TableHeader>
                            <TableHeader />
                        </tr>
                    </thead>
                    <tbody>
                        {classes && classes.map(item => (
                            <TableRow key={item.classId}>
                                <TableCell>{item.className}</TableCell>
                                <TableCell>{item.date}</TableCell>
                                <TableCell>{item.time}</TableCell>
                                <TableCell>{item.trainerName}</TableCell>
                                <TableCell>{item.roomName}</TableCell>
                                <TableCell>{type === "Upcoming" ? <LeaveClassButton classId={item.classId}/> :  <DummyView/>}</TableCell>
                            </TableRow>
                        ))}
                    </tbody>
                </Table>
                 </>
            )}
        </Container>
    );
}

export default MemberClassesTable;

const Container = styled.div`
    margin-top: 20px;
`;

const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
`;

const TableHeader = styled.th`
    padding: 10px 100px 10px 50px;
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
    padding: 20px 100px 20px 50px;
    border-bottom: 1px solid #ddd;
    text-align: center;
    font-size: 20px;
`;

const LeaveButton = styled.button`
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