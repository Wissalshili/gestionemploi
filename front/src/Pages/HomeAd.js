import React, { useState, useEffect, useCallback } from 'react';

import Modal from 'react-modal';
import { useNavigate } from 'react-router-dom';

    Modal.setAppElement('#root');

    const Home = () => {
      const [adminId, setadminId] = useState('');
      const navigate = useNavigate();
      const [userNom, setUserNom] = useState('');
      const [userPrenom, setUserPrenom] = useState('');
      const [usernum, setUsernum] = useState('');
      const [useremail, setUseremail] = useState('');
      const [userpass, setUserpass] = useState('');

      
      const fetchData = useCallback(async () => {
        try {
          const adminId = localStorage.getItem('adminid');
          const adminNom = localStorage.getItem('adminNom');
          const adminPrenom = localStorage.getItem('adminPrenom');
          const adminnum = localStorage.getItem('adminnum');
          const adminemail = localStorage.getItem('useremail');
          const adminpass = localStorage.getItem('userpassword');
          setadminId(parseInt(adminId, 10));
          setUserNom(adminNom);
          setUserPrenom(adminPrenom);
          setUsernum(adminnum);
          setUseremail(adminemail);
          setUserpass(adminpass);

        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }, []);

      useEffect(() => {
        const userIdFromStorage = localStorage.getItem('userId');
        setadminId(parseInt(userIdFromStorage, 10));
      }, []);



      useEffect(() => {
        fetchData();
      }, [adminId, fetchData]);

      const userType = localStorage.getItem('userType');
      if ( userType == null || userType !== 'Admin' ) {
        navigate('/error-page');
      }
      
      return (
          <div className="home-container">
            <div style={{ display: 'flex', flexDirection: 'row' }}>
              
              <div style={{ flex: 5 }}>
                <div className="gray-box">
                  
                <h2>Admin Space</h2>
                <p><strong>id:</strong> {adminId}</p>
              <p><strong>Nom:</strong> {userNom}</p>
              <p><strong>Prénom:</strong> {userPrenom}</p>
              <p><strong>Numéro du tel:</strong> {usernum}</p>
              <p><strong>Adresse email:</strong> {useremail}</p>
              <p><strong>Mot de passe:</strong> {userpass}</p>
                     
                </div>
              </div>
            </div>
        </div>
      );
    };

    export default Home;
