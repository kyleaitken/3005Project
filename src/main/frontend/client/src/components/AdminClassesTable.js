import styled from "styled-components";
import UpdateClassForm from "./UpdateClassForm";

const AdminClassesTable = (props) => {
    const {classes, rooms, trainers, tryUpdateClass, handleDeleteClass} = props;
    const noClasses = !classes || classes.length === 0;

    const handleDeleteClassClicked = async (classId) => {
        handleDeleteClass(classId);
    }

    
    return (
        <AdminClassesView>
        <h2>Classes</h2>
        {!noClasses && classes.map(classData => (
                <ClassView key={classData.classId}>
                    <UpdateClassForm
                        classData={classData}
                        trainers={trainers}
                        rooms={rooms}
                        handleUpdateClass={tryUpdateClass}
                    />
                    <DeleteClassButton onClick={() => handleDeleteClassClicked(classData.classId)}>Delete Class</DeleteClassButton>
                </ClassView>
        ))}
        </AdminClassesView>
    );
}

export default AdminClassesTable;

const AdminClassesView = styled.div`
    display: flex;
    flex-direction: column;
    margin-left: 40px;
`

const ClassView = styled.div``;

const DeleteClassButton = styled.button`
    padding: 5px 5px;
    margin: 0px 0px 50px 40px;
    width: 150px;
    align-self: center;
  
    background-color: #E63333;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
  
    &:hover {
        background-color: darkred;
    }
`
