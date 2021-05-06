package org.example.ssh;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class HelloSSH {
    static class EasySsh {
        protected JSch jsch;
        protected Session session;
        protected ChannelSftp sftp;

        public EasySsh() {
            this.jsch = new JSch();
        }

        public boolean connect(
                String host, int port,
                String username, String password,
                Properties sshConfig
        ) {
            try {
                this.session = this.jsch.getSession(username, host, port);
                this.session.setPassword(password);
                sshConfig.put("StrictHostKeyChecking", "no");
                this.session.setConfig(sshConfig);
                this.session.setTimeout(1000 * 10);
                this.session.connect();
                //
                this.sftp = (ChannelSftp) this.session.openChannel("sftp");
                this.sftp.connect();
                //
                return true;
            } catch (JSchException e) {
                System.err.println(e.getMessage());
            }
            return false;
        }

        public boolean connect(String host, int port, String username, String password) {
            return connect(host, port, username, password, new Properties());
        }

        public boolean connect(String host, String username, String password) {
            return connect(host, 22, username, password, new Properties());
        }

        public void close() {
            if (this.sftp != null) {
                this.sftp.disconnect();
            }
            if (this.session != null) {
                this.session.disconnect();
            }
        }

        public ChannelSftp getSftp() {
            return this.sftp;
        }

        public boolean upLoad(String srcFileName, String dstFileName) {
            try {
                this.sftp.put(srcFileName, dstFileName);
                return true;
            } catch (SftpException e) {
                System.err.println(e.getMessage());
            }
            return false;
        }

        public boolean upLoad(StringBuilder data, String dstFileName) {
            File tempFile = null;
            try {
                tempFile = File.createTempFile("~upload", ".TMP");
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(data.toString().getBytes());
                fos.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return false;
            } finally {
                if (null != tempFile) {
                    tempFile.deleteOnExit();
                }
            }
            return upLoad(tempFile.getAbsolutePath(), dstFileName);
        }
    }


    public static void main(String[] args) {
        EasySsh ssh = new EasySsh();
        final String host = "192.168.1.100";
        final String usr = "root";
        final String pwd = "root";
        if (ssh.connect(host, usr, pwd)) {
            StringBuilder builder = new StringBuilder();
            builder.append("Hello");
            System.out.println(ssh.upLoad(builder, "./Hello.txt"));
            ssh.close();
        }
    }
}
