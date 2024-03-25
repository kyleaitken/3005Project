import { ADMIN_API_ENDPOINTS } from './config';
import { get, del, post, put, putWithBody} from './utils';


export const getAllClasses = () => {
    const url = ADMIN_API_ENDPOINTS.getAllClasses();
    return get(url);
};

export const getRooms = () => {
    const url = ADMIN_API_ENDPOINTS.getRooms();
    return get(url);
}

export const addClass = (className, date, time, trainerName, roomName) => {
    return post(ADMIN_API_ENDPOINTS.addClass(), {date, time, roomName, trainerName, className});
};

export const updateClass = (classId, classUpdateRequest) => {
    return putWithBody(ADMIN_API_ENDPOINTS.updateClass(classId), classUpdateRequest);
};

export const deleteClass = (classId) => {
    const url = ADMIN_API_ENDPOINTS.deleteClass(classId);
    return del(url);
}