import React from 'react';
import styled from 'styled-components';

const GoalsTable = (props) => {
    const {handleCompleteGoal, handleAddGoal, handleDeleteGoal, goals, type} = props;
    const noGoals = !goals || goals.length === 0;

    const ButtonContainer = ({ goalId }) => (
        <ButtonView>
                <CompleteGoalButton onClick={() => handleCompleteGoal(goalId)}>
                    Complete Goal
                </CompleteGoalButton>
                <DeleteGoalButton onClick={() => handleDeleteGoal(goalId)}>
                    Delete Goal
                </DeleteGoalButton>
        </ButtonView>
    );

    const Header = () => {
        return (
            <HeaderView>
                    {noGoals ?  (<h1>No {type} Goals</h1>) : 
                    (<h1>{type} Goals</h1>)}
            </HeaderView>
            )
    }

    return (
        <Container>
            {noGoals ? <Header /> : (
            <>
                <Header/>
                <Table>
                    <thead>
                        <tr>
                            <TableHeader>Goal</TableHeader>
                            <TableHeader>{type==="Completed" ? <p>Completed Date</p> : <p>Target Date</p>}</TableHeader>
                            <TableHeader />
                        </tr>
                    </thead>
                    <tbody>
                        {goals && goals.map(item => (
                            <TableRow key={item.goalId}>
                                <TableCell>{item.description}</TableCell>
                                <TableCell>{type==="Completed" ? item.completedDate : item.targetDate}</TableCell>
                                <TableCell>{type === "In Progress"
                                ? <ButtonContainer goalId={item.goalId}/> :  <DummyView/>}</TableCell>
                            </TableRow>
                        ))}
                    </tbody>
                </Table>
            </>
            )}
        </Container>
    );
}

export default GoalsTable;

const Container = styled.div`
    margin: 50px 0px;
`;

const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
`;

const TableHeader = styled.th`
    padding: 10px 60px 10px 50px;
    border-bottom: 1px solid #ddd;
    text-align: left;
    font-size: 22px;
`;

const TableRow = styled.tr`
    &:nth-child(even) {
        background-color: #f2f2f2;
    }
`;

const TableCell = styled.td`
    padding: 20px 60px 20px 50px;
    border-bottom: 1px solid #ddd;
    font-size: 16px;
`;

const DeleteGoalButton = styled.button`
    padding: 10px 10px;
    margin: 0px 10px;
    background-color: red; 
    width: 170px;
    align-self: flex-end;
    color: white;
    border: none;
    cursor: pointer;
    font-size: 14px;
    border-radius: 5px;

    &:hover {
        background-color: darkred; 
    }
`

const CompleteGoalButton = styled.button`
    padding: 10px 0px;
    margin: 0px 10px;
    background-color: green; 
    width: 170px;
    align-self: flex-end;
    color: white;
    border: none;
    cursor: pointer;
    font-size: 14px;
    border-radius: 5px;

    &:hover {
        background-color: darkgreen; 
    }
`

const DummyView = styled.div`
    width: 170px;
`

const HeaderView = styled.div`
    margin-left: 50px;
    margin-bottom: 50px;
`

const ButtonView = styled.div`
    display: flex;
    flex-direction: row;
`