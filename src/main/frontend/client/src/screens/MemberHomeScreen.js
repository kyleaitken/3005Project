import { Navigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import NavigationSideBar from '../components/NavigationSideBar';

const MemberHomeScreen = () => {
    const { userId } = useAuth();

    if (!userId) {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    return (
        <NavigationSideBar />
    )
}

export default MemberHomeScreen;