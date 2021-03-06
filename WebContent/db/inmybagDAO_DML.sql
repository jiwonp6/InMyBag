--query
--?��?��?��ADMIN?��?��?��
    --?��INSERT MINI DATA
    INSERT INTO ADMIN (aID, aPW, aNAME, aKING) VALUES ('aKING', '111', '최고관리자', 1);
    commit;
    INSERT INTO ADMIN (aID, aPW, aNAME) VALUES ('aaa', '111', '박박�?');
    SELECT * FROM ADMIN;
    SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
					 (SELECT * FROM ADMIN ORDER BY aKING DESC, aID ) A)
					 WHERE RN BETWEEN 1 AND 100;
    --(1)LOGIN CHECK
    SELECT * FROM ADMIN WHERE aID='aaa' and aPW='111';
    --(2)get ADMIN DTO(for session)
    SELECT * FROM ADMIN WHERE aID='aaa';
    --(3)aID CONFIRM
    SELECT * FROM ADMIN WHERE aID='aaa';
    --(4)JOIN ADMIN
    INSERT INTO ADMIN (aID, aPW, aNAME) VALUES ('aaa', '111', '박박�?');
    --(5)MODIFY ADMIN
    UPDATE ADMIN SET aPW='111',
                     aNAME='�?'
            WHERE aID='aaa';
    COMMIT;
    --(6)WITHDRAWAL ADMIN
    DELETE FROM ADMIN WHERE aID='aaa'; 
    ROLLBACK;
    --(7)aKING CHECK
    SELECT * FROM ADMIN WHERE aID='aKING' AND aKING=1;
--?��?��?��MEMBER?��?��?��
    --?��INSERT MINI DATA
    INSERT INTO MEMBER (mID, mPW, mNAME, mBIRTH, mEMAIL) VALUES ('aaa', '111', '박박�?', '99-09-09', 'park@park.com');
    SELECT * FROM MEMBER;
    select * from member where mId like '%'||'aaa'||'%';
    --(1)LOGIN CHECK
    SELECT * FROM MEMBER WHERE mID='aaa' and mPW='111';
    --(2)get MEMBER DTO(for session)
    SELECT * FROM MEMBER WHERE mID='aaa';
    --(3)mID CONFIRM
    SELECT * FROM MEMBER WHERE mID='aaa';
    --(4)JOIN MEMBER
    INSERT INTO MEMBER (mID, mPW, mNAME, mBIRTH, mEMAIL) VALUES ('aaa', '111', '박박�?', '99-09-09', 'park@park.com');
    --(5)MODIFY MEMBER
    UPDATE MEMBER SET mPW='111',
                     mNAME='�?',
                     mBIRTH='99-09-09',
                     mEMAIL='parkP@park.com'
            WHERE mID='aaa';
    COMMIT;
    --(6)MEMBER LIST
    SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER ORDER BY mRDATE DESC) A)
        WHERE RN BETWEEN 2 AND 3;
    --(7)COUNT MEMBER
    SELECT COUNT(*) mCNT FROM MEMBER;
    --(8)WITHDRAWAL MEMBER
    DELETE FROM MEMBER WHERE mID='aaa'; 
    ROLLBACK;
    --(9)mEMAIL CONFIRM
    SELECT * FROM MEMBER WHERE mEMAIL='parkP@park.com';
--?��?��?��FAQBOARD?��?��?��
    INSERT INTO FAQBOARD (fID, aID, fTITLE, fCONTENT, fFILENAME, fIP) 
        VALUES (FAQ_SEQ.NEXTVAL, 'aaa', 'FAQ!', 'answer', null, '112.169.33.198');
    SELECT * FROM FAQBOARD;
--?��?��?��NOTICEBOARD?��?��?��
     --?��INSERT MINI DATA
    INSERT INTO NOTICEBOARD (nID, aID, nTITLE, nCONTENT, nFILENAME, nIP) 
        VALUES (NOTICE_SEQ.NEXTVAL, 'aaa', 'NOTICE!', 'notice, notice', null, '112.169.33.198');
    SELECT * FROM NOTICEBOARD;
    --(1)LIST(startROW~endROW)
    SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT N.* FROM NOTICEBOARD N ORDER BY nID DESC) A)
        WHERE RN BETWEEN 2 AND 3;
    --(2)COUNT NOTICEBOARD
    SELECT COUNT(*) nCNT FROM NOTICEBOARD;
    --(3)WRITE NOTICEBOARD(NO REPLY)
    INSERT INTO NOTICEBOARD (nID, aID, nTITLE, nCONTENT, nFILENAME, nIP) 
        VALUES (NOTICE_SEQ.NEXTVAL, 'aaa', 'NOTICE!', 'notice, notice', null, '112.169.33.198');
    --(4)HIT UP
    UPDATE NOTICEBOARD SET nHIT = nHIT +1 WHERE nID='1';
    --(5)get NOTICEBOARD DTO(for session)
    SELECT * FROM NOTICEBOARD WHERE nID='1';
    --(6)MODIFY NOTICEBOARD
    UPDATE NOTICEBOARD SET nTITLE = 'NOTICE!!',
                           nCONTENT = 'notice~!',
                           nFILENAME = NULL,
                           nRDATE = SYSDATE,
                           nIP = '112.169.33.198'
                        WHERE nID=1;
    COMMIT;
    --(7)DELETE NOTICEBOARD
    DELETE FROM NOTICEBOARD WHERE nID=1;
    ROLLBACK;
--?��?��?��QNABOARD?��?��?��
     --?��INSERT MINI DATA
    INSERT INTO QNABOARD (qID, mID, qTITLE, qCONTENT, qFILENAME, qGROUP, qSTEP, qINDENT, qIP) 
        VALUES (QNA_SEQ.NEXTVAL, 'aaa', 'QNA!', 'qnaqna', null, QNA_SEQ.CURRVAL, 0, 0, '112.169.33.198');
    INSERT INTO QNABOARD (qID, mID, qTITLE, qCONTENT, qFILENAME, qGROUP, qSTEP, qINDENT, qIP) 
        VALUES (QNA_SEQ.NEXTVAL, 'aaa', 'QNAQNA', 'qnaqna2', null, QNA_SEQ.CURRVAL, 0, 0, '112.169.33.198');
    SELECT * FROM QNABOARD where mid='aaa';
    commit;
    --(1)LIST(startROW~endROW)
    SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM QNABOARD ORDER BY qGROUP DESC, qSTEP) A)
        WHERE RN BETWEEN 1 AND 3;
    SELECT * FROM  (SELECT ROWNUM RN, A.* FROM  (SELECT * FROM QNABOARD 
					 WHERE mID='aaa' ORDER BY qGROUP DESC, qSTEP) A) WHERE RN BETWEEN 1 AND 5;
    --(2)COUNT QNABOARD
    SELECT COUNT(*) qCNT FROM QNABOARD;
    --(3)WRITE QNABOARD
    INSERT INTO QNABOARD (qID, mID, qTITLE, qCONTENT, qFILENAME, qGROUP, qSTEP, qINDENT, qIP) 
        VALUES (QNA_SEQ.NEXTVAL, 'aaa', 'QNA!', 'qnaqna', null, QNA_SEQ.CURRVAL, 0, 0, '112.169.33.198');
    --(4)HIT UP
    UPDATE QNABOARD SET qHIT = qHIT +1 WHERE qID='1';
    --(5)get QNABOARD DTO(for CONTENT)
    SELECT * FROM QNABOARD WHERE qID='1';
    --(6)MODIFY QNABOARD
    UPDATE QNABOARD SET qTITLE = 'QNA!!',
                           qCONTENT = 'qnaQna',
                           qFILENAME = NULL,
                           qRDATE = SYSDATE,
                           qIP = '112.169.33.198'
                        WHERE qID=5;
    COMMIT;
    select * from qnaBoard where qid='5';
    --(7)DELETE QNABOARD
    DELETE FROM QNABOARD WHERE qID=1;
    ROLLBACK;
    --(8)COMMENT
        --STEP a
        UPDATE QNABOARD SET qSTEP = qSTEP+1
            WHERE qGROUP=1 AND qSTEP>1;
    INSERT INTO QNABOARD (qID, aID, qTITLE, qCONTENT, qFILENAME, qGROUP, qSTEP, qINDENT, qIP) 
        VALUES (QNA_SEQ.NEXTVAL, 'aaa', 'QNA_REPLY', 'replys', null, 1, 1, 1, '112.169.33.100');
   --(9)ALL DELETE QNABOARD
   DELETE FROM QNABOARD WHERE mID = 'aaa';
--?��?��?��ITEMBOARD?��?��?��
     --?��INSERT MINI DATA
    INSERT INTO ITEMBOARD (iID, mID, iTITLE, iCONTENT, iFILENAME, iGROUP, iSTEP, iINDENT, iIP) 
        VALUES (ITEM_SEQ.NEXTVAL, 'aa3', 'ITEM?', 'item', null, ITEM_SEQ.CURRVAL, 0, 0, '112.169.33.198');
    SELECT * FROM ITEMBOARD;
    --(1)LIST(startROW~endROW)
    SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT I.* FROM ITEMBOARD I ORDER BY iGROUP DESC, iSTEP) A)
        WHERE RN BETWEEN 1 AND 3;
    SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT I.* FROM ITEMBOARD I WHERE mID='aa3' ORDER BY iGROUP DESC, iSTEP) A)
        WHERE RN BETWEEN 1 AND 3;
    SELECT * FROM ITEMBOARD WHERE ;
    --(2)COUNT ITEMBOARD
    SELECT COUNT(*) iCNT FROM ITEMBOARD;
    --(3)WRITE ITEMBOARD
    INSERT INTO ITEMBOARD (iID, mID, iTITLE, iCONTENT, iFILENAME, iGROUP, iSTEP, iINDENT, iIP) 
        VALUES (ITEM_SEQ.NEXTVAL, 'aaa', 'ITEM?', 'item', null, ITEM_SEQ.CURRVAL, 0, 0, '112.169.33.198');
    --(4)HIT UP
    UPDATE ITEMBOARD SET iHIT = iHIT +1 WHERE iID='2';
    --(5)get ITEMBOARD DTO(for CONTENT)
    SELECT * FROM ITEMBOARD WHERE iID='2';
    --(6)MODIFY ITEMBOARD
    UPDATE ITEMBOARD SET iTITLE = 'ITEM??',
                           iCONTENT = 'itemItem',
                           iFILENAME = NULL,
                           iRDATE = SYSDATE,
                           iIP = '112.169.33.198'
                        WHERE iID=2;
    COMMIT;
    --(7)DELETE ITEMBOARD
    DELETE FROM ITEMBOARD WHERE iID=2;
    ROLLBACK;
    --(8)COMMENT
        --STEP a
        UPDATE ITEMBOARD SET iSTEP = iSTEP+1
            WHERE iGROUP=2 AND iSTEP>1;
    INSERT INTO ITEMBOARD (iID, mID, iTITLE, iCONTENT, iFILENAME, iGROUP, iSTEP, iINDENT, iIP) 
        VALUES (QNA_SEQ.NEXTVAL, 'aaa', 'ITEM_REPLY', 'replys', null, 2, 1, 1, '112.169.33.100');
   --(9)ALL DELETE ITEMBOARD
   DELETE FROM ITEMBOARD WHERE mID = 'aaa';    
--?��?��?��myBAGBOARD?��?��?��
     --?��INSERT MINI DATA
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'THISISMYBAG', 'bag', null, '112.169.33.198');
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'MYBAG', 'bag!!!1', 'myBag.jpg', '112.169.33.198');
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'yes MYBAG', 'bag~~~', 'myBag.jpg', '112.169.33.198');
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'yes MYBAG2', 'bag2', null, '112.169.33.198');
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'yes MYBAG2', 'bag2', null, '112.169.33.198');
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'yes MYBAG2', 'bag2', null, '112.169.33.198');
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'yes MYBAG2', 'bag2', null, '112.169.33.198');
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'yes MYBAG2', 'bag2', null, '112.169.33.198');
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'yes MYBAG2', 'bag2', null, '112.169.33.198');
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'yes MYBAG2', 'bag2', null, '112.169.33.198');
    SELECT * FROM myBAGBOARD;
    commit;
    --(1)LIST(startROW~endROW)
    SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT B.* FROM myBAGBOARD B ORDER BY bID DESC) A)
        WHERE RN BETWEEN 1 AND 3;
    --(2)COUNT myBAGBOARD
    SELECT COUNT(*) bCNT FROM myBAGBOARD;
    --(3)WRITE myBAGBOARD
    INSERT INTO myBAGBOARD (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) 
        VALUES (myBAG_SEQ.NEXTVAL, 'aaa', 'THISISMYBAG', 'bag', null, '112.169.33.198');
    --(4)HIT UP
    UPDATE myBAGBOARD SET bHIT = bHIT +1 WHERE bID='1';
    --(5)get myBAGBOARD DTO(for CONTENT)
    SELECT * FROM myBAGBOARD WHERE B.bID='3';
    SELECT B.*, C.cCONTENT FROM myBAGBOARD B, myBAGCANVAS C WHERE B.bID=C.bID AND B.bID='3';
    --(6)MODIFY myBAGBOARD
    UPDATE myBAGBOARD SET bNAME = 'THISISMYBAG!!!',
                           bCONTENT = 'myBAG',
                           bFILENAME = NULL,
                           bRDATE = SYSDATE,
                           bIP = '112.169.33.198'
                        WHERE bID=2;
    COMMIT;
    --(7)DELETE myBAGBOARD
    DELETE FROM myBAGBOARD WHERE bID=1;
    ROLLBACK;
    --(8)ALL DELETE myBAGBOARD
    DELETE FROM myBAGBOARD WHERE mID = 'aaa';
    SELECT * FROM myBAGBOARD;
--?��?��?��myBAGCANVAS?��?��?�� 
    --?��INSERT MINI DATA
    INSERT INTO myBAGCANVAS (cID, bID, cX, cY, cCONTENT) 
        VALUES (CANVAS_SEQ.NEXTVAL, 3, 1, 1, 'FROMCCANVAS');
    SELECT * FROM myBAGCANVAS;
--?��?��?��REPLYmyBAG?��?��?�� 
    --?��INSERT MINI DATA
    INSERT INTO REPLYmyBAG (rID, mID, bID, rCONTENT, rGROUP, rSTEP, rINDENT, rIP) 
        VALUES (REPLY_SEQ.NEXTVAL, 'aaa', 13, 'reply~!', REPLY_SEQ.CURRVAL, 0, 0, '112.169.33.198');
    SELECT * FROM REPLYmyBAG;
    COMMIT;
    --(1)LIST(startROW~endROW)
    SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT R.* FROM REPLYmyBAG R ORDER BY rGROUP DESC, rSTEP) A)
        WHERE RN BETWEEN 1 AND 3;
    --(2)COUNT REPLYmyBAG
    SELECT COUNT(*) rCNT FROM REPLYmyBAG;
    --(3)WRITE REPLYmyBAG
    INSERT INTO REPLYmyBAG (rID, mID, rCONTENT, rGROUP, rSTEP, rINDENT, rIP) 
        VALUES (REPLY_SEQ.NEXTVAL, 'aaa', 'ILIKETHIS', REPLY_SEQ.CURRVAL, 0, 0, '112.169.33.198');
    --(4)get REPLYmyBAG DTO(for CONTENT)
    SELECT * FROM REPLYmyBAG WHERE bID='3';
    --(5)MODIFY REPLYmyBAG
    UPDATE REPLYmyBAG SET rCONTENT = 'LIKETHIS!',
                           rRDATE = SYSDATE,
                           rIP = '112.169.33.198'
                        WHERE rID=3;
    COMMIT;
    --(6)DELETE REPLYmyBAG
    DELETE FROM REPLYmyBAG WHERE rID=3;
    ROLLBACK;
    --(7)COMMENT
        --STEP a
        UPDATE REPLYmyBAG SET rSTEP = rSTEP+1
            WHERE rGROUP=2 AND rSTEP>1;
    INSERT INTO REPLYmyBAG (rID, mID, rCONTENT, rGROUP, rSTEP, rINDENT, rIP) 
        VALUES (REPLY_SEQ.NEXTVAL, 'aaa', 'REPLY_REPLY', 3, 1, 1, '112.169.33.100');
   --(8)ALL DELETE ITEMBOARD
   DELETE FROM REPLYmyBAG WHERE mID = 'aaa';

SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT B.* FROM LIKEmyBAG L, myBAGBOARD B 
    WHERE L.mID='aaa' AND L.BID=B.BID ORDER BY lRDATE DESC) A) WHERE RN BETWEEN 1 AND 3;
select * from likemybag;


--?��?��?��LIKEmyBAG?��?��?�� 
    INSERT INTO LIKEmyBAG (LID, mID, bID) 
        VALUES (LIKE_SEQ.NEXTVAL, 'aaa', 1);
    --(1)LIKE BAG
    INSERT INTO LIKEmyBAG (LID, mID, bID) 
        VALUES (LIKE_SEQ.NEXTVAL, 'aaa', 2);
    COMMIT;
    --(2)DISLIKE BAG
    DELETE FROM LIKEmyBAG WHERE mID='aaa' AND bID=1;
    ROLLBACK;
    --(3)LIKE_LIST(startROW~endROW)
    SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT B.* FROM myBAGBOARD B, LIKEmyBAG L 
                                                    WHERE B.BID=L.LID AND L.MID='aaa' ORDER BY lRDATE DESC) A)
        WHERE RN BETWEEN 1 AND 3;
    
    
        