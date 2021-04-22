FROM maven:3.8.1-openjdk-11
WORKDIR /prog
COPY . /prog
CMD ["bash", "run.sh"]
