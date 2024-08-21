import React, { useEffect, useRef } from 'react';
import 'ace-builds/src-noconflict/ace';
import 'ace-builds/src-noconflict/mode-html';
import 'ace-builds/src-noconflict/mode-css';
import 'ace-builds/src-noconflict/mode-javascript';
import 'ace-builds/src-noconflict/theme-monokai';
import 'ace-builds/src-noconflict/ext-language_tools';
import 'ace-builds/src-min-noconflict/ext-language_tools';
import 'ace-builds/webpack-resolver';
import './style.css';

const LiveCodeEditor = ({ editorData }) => {
  const htmlEditorRef = useRef(null);
  const cssEditorRef = useRef(null);
  const jsEditorRef = useRef(null);
  const outputRef = useRef(null);

  useEffect(() => {
    const ace = require('ace-builds');

    const htmlEditor = ace.edit(htmlEditorRef.current);
    const cssEditor = ace.edit(cssEditorRef.current);
    const jsEditor = ace.edit(jsEditorRef.current);

    [htmlEditor, cssEditor, jsEditor].forEach((editor) => {
      editor.setTheme('ace/theme/monokai');
      editor.setOptions({
        fontSize: '14px',
        wrap: true,
        showPrintMargin: false,
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: false,
        fontFamily: 'Courier, monospace',
        foldStyle: 'manual',
        useWorker: false
      });
    });

    htmlEditor.session.setMode('ace/mode/html');
    cssEditor.session.setMode('ace/mode/css');
    jsEditor.session.setMode('ace/mode/javascript');

    const run = () => {
      const htmlCode = htmlEditor.getValue();
      const cssCode = cssEditor.getValue();
      const jsCode = jsEditor.getValue();
      const output = outputRef.current;

      output.contentDocument.open();
      output.contentDocument.write(htmlCode + '<style>' + cssCode + '</style>');
      output.contentDocument.close();
      output.contentWindow.document.body.innerHTML += '<script>' + jsCode + '<\/script>';
    };

    [htmlEditor, cssEditor, jsEditor].forEach((editor) => {
      editor.getSession().on('change', () => {
        clearTimeout(editor.changeTimeout);
        editor.changeTimeout = setTimeout(run, 300);
      });
    });

    // Set default code
    htmlEditor.setValue('<div>Hello, World!</div>', -1);
    cssEditor.setValue('div { color: red; }', -1);
    jsEditor.setValue('console.log("Hello, World!");', -1);

    // Run initial code
    run();
  }, []);

  const handleSubmit = async () => {
    const htmlCode = htmlEditorRef.current.env.editor.getValue();
    const cssCode = cssEditorRef.current.env.editor.getValue();
    const jsCode = jsEditorRef.current.env.editor.getValue();
  
    // Prompt the user for a title and description
    const title = window.prompt("Please enter a title for your code:");
    const description = window.prompt("Please enter a description:");
  
    if (!title || !description) {
      alert("Title and description are required.");
      return;
    }
  
    const payload = {
      UserID: editorData, // Username passed from Navbar
      Title: title,
      Description: description,
      HTMLCode: htmlCode,
      CSSCode: cssCode,
      JSCode: jsCode,
    };
  
    try {
      const response = await fetch('https://3j51dwtcd5.execute-api.us-east-1.amazonaws.com/Cors/api/snippets', { // Replace with your actual API endpoint
        mode: 'no-cors',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });
  
      if (response.ok) {
        alert('Code saved successfully!');
      } else {
        alert('Failed to save code.');
      }
    } catch (error) {
      console.error('Error:', error);
      alert('An error occurred while saving the code.');
    }
  };

  return (
    <div className="container">
      <div className="left">
        <label><i className="fa-brands fa-html5"></i> HTML</label>
        <div id="html-editor" ref={htmlEditorRef} className="code-editor"></div>

        <label><i className="fa-brands fa-css3-alt"></i> CSS</label>
        <div id="css-editor" ref={cssEditorRef} className="code-editor"></div>

        <label><i className="fa-brands fa-js"></i> JavaScript</label>
        <div id="js-editor" ref={jsEditorRef} className="code-editor"></div>

        <button onClick={handleSubmit} className="submit-button">Submit</button>
      </div>
      <div className="right">
        <label><i className="fa-solid fa-play"></i> Output</label>
        <iframe id="output" ref={outputRef} title="Output"></iframe>
      </div>
    </div>
  );
};

export default LiveCodeEditor;