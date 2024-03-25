import styled from "styled-components";
import { useAuth } from "../contexts/AuthContext";
import NavigationSideBar from "../components/NavigationSideBar";
import AdminNavigationSideBar from "../components/AdminNavigationSideBar";
import LoginScreen from "../screens/LoginScreen";
import MemberSchedule from "./MemberSchedule";
import MemberSessions from "./MemberSessions";
import MemberClasses from "./MemberClasses";
import AvailableClasses from "./AvailableClasses";
import ExerciseRoutinesContainer from "./ExerciseRoutinesContainer";
import MemberGoals from "./MemberGoals";
import MemberInvoices from "./MemberInvoices";
import MemberHealthInfo from "./MemberHealthInfo";
import MemberProfile from "./MemberProfile";
import AdminClassContainer from "./AdminClassContainer";
import AdminInvoiceContainer from "./AdminInvoiceContainer";
import EquipmentContainer from "./EquipmentContainer";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';



const AppContent = () => {
    const { isLoggedIn, userType } = useAuth();

    return (
        <AppContentView>
            {isLoggedIn && userType ==="Member" ? <NavigationSideBar /> : <AdminNavigationSideBar />}
            <Routes>
                <Route path="/" element={<LoginScreen />} />
                <Route path="/schedule" element={<MemberSchedule />} />
                <Route path="/sessions" element={<MemberSessions />} />
                <Route path="/classes" element={<MemberClasses />} />
                <Route path="/joinClass" element={<AvailableClasses />} />
                <Route path="/routines" element={<ExerciseRoutinesContainer />} />
                <Route path="/goals" element={<MemberGoals />} />
                <Route path="/memberInvoices" element={<MemberInvoices />} />
                <Route path="/healthInfo" element={<MemberHealthInfo/>} />
                <Route path="/profile" element={<MemberProfile />} />
                <Route path="/adminClasses" element={<AdminClassContainer />} />
                <Route path="/adminInvoices" element={<AdminInvoiceContainer />} />
                <Route path="/equipment" element={<EquipmentContainer/>} />
            </Routes>
        </AppContentView>
    )

}


export default AppContent;

const AppContentView = styled.div`
    display: flex;
`