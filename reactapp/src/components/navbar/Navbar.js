import React from 'react';
import { signOut } from 'aws-amplify/auth';
import { useAuthenticator } from '@aws-amplify/ui-react';
import { Nav, NavLink, Bars, NavMenu, NavBtn } from './NavbarElements';
import { useNavigate } from 'react-router-dom';

async function handleSignOut() {
  try {
    await signOut();
    window.location.href = '/signin';
  } catch (error) {
    console.log('error signing out: ', error);
  }
}

const Navbar = ({ setEditorData }) => {
  const { user } = useAuthenticator((context) => [context.user]);
  const navigate = useNavigate();

  const handleEditorNavigation = () => {
    if (user) {
      setEditorData(user.username);
      navigate('/code-editor');
    }
  };

  return (
    <Nav>
      <NavLink to='/'>
        Logo
      </NavLink>
      <Bars />
      <NavMenu>
        <NavLink to='/code-editor' activeStyle onClick={handleEditorNavigation}>
          HTML
        </NavLink>
        <NavLink to='/services'>
          CSS
        </NavLink>
        <NavLink to='/contact-us'>
          JS
        </NavLink>
        <NavLink to='/sign-up'>
          Sign Up
        </NavLink>
        {user && (
          <>
            <NavLink to='/favorites'>
              Favorites
            </NavLink>
            <span style={{ marginLeft: '20px', color: 'white' }}>
              Hello, {user.username}!
            </span>
          </>
        )}
      </NavMenu>
      <NavBtn>
        <button onClick={handleSignOut}>Sign Out</button>
      </NavBtn>
    </Nav>
  );
};

export default Navbar;
