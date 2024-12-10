import React, { useState, useEffect, useCallback } from 'react';

import Modal from 'react-modal';
import { useNavigate } from 'react-router-dom';

    Modal.setAppElement('#root');

    const Home = () => {
      const [teacherId, setteacherId] = useState('');
      const navigate = useNavigate();
      const [userNom, setUserNom] = useState('');
      const [userPrenom, setUserPrenom] = useState('');
      const [usernum, setUsernum] = useState('');
      const [userdep, setUserdep] = useState('');
      const [useremail, setUseremail] = useState('');
      const [userpass, setUserpass] = useState('');

      
      const fetchData = useCallback(async () => {
        try {
          const teacherId = localStorage.getItem('teacherid');
          const teacherNom = localStorage.getItem('teachernom');
          const teacherPrenom = localStorage.getItem('teacherprenom');
          const teachernum = localStorage.getItem('teacherphone');
          const teacherdep = localStorage.getItem('teacherdepartment');
          const teacheremail = localStorage.getItem('useremail');
          const teacherpass = localStorage.getItem('userpassword');
          setteacherId(parseInt(teacherId, 10));
          setUserNom(teacherNom);
          setUserPrenom(teacherPrenom);
          setUsernum(teachernum);
          setUserdep(teacherdep);
          setUseremail(teacheremail);
          setUserpass(teacherpass);

        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }, []);

      useEffect(() => {
        const userIdFromStorage = localStorage.getItem('userId');
        setteacherId(parseInt(userIdFromStorage, 10));
      }, []);



      useEffect(() => {
        fetchData();
      }, [teacherId, fetchData]);

      const userType = localStorage.getItem('userType');
      if ( userType == null || userType !== 'Teacher' ) {
        navigate('/error-page');
      }
      
      return (
          <div className="home-container">
            <div style={{ display: 'flex', flexDirection: 'row' }}>
              
              <div style={{ flex: 5 }}>
                <div className="gray-box">
                  
              <h2>Teacher Space</h2>
              <p><strong>id:</strong> {teacherId}</p>
              <p><strong>Nom:</strong> {userNom}</p>
              <p><strong>Prénom:</strong> {userPrenom}</p>
              <p><strong>Numéro du tel:</strong> {usernum}</p>
              <p><strong>Department:</strong> {userdep}</p>
              <p><strong>Adresse email:</strong> {useremail}</p>
              <p><strong>Mot de passe:</strong> {userpass}</p>
                     
                </div>
              </div>
            </div>
        </div>
      );
    };

    export default Home;
