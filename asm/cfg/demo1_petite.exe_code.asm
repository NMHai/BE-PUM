0x0040a10f:	movl %eax, $0x40a000<UINT32>
0x0040a114:	pushl $0x0<UINT8>
0x0040a116:	pushl $0x408055<UINT32>
0x0040a11b:	pushl %fs:0
0x0040a122:	movl %fs:0, %esp
0x0040a129:	pushfw
0x0040a12b:	pusha
0x0040a12c:	pushl %eax
0x0040a12d:	movl %ebx, %eax
0x0040a12f:	addl %eax, (%eax)
0x0040a131:	pushl $0x32c<UINT32>
0x0040a136:	pushl $0x0<UINT8>
0x0040a138:	call GlobalAlloc@kernel32.dll
GlobalAlloc@kernel32.dll: API Node	
0x0040a13b:	movl 0x8(%ebx), %eax
0x0040a13e:	movl %eax, %ebx
0x0040a140:	addl %eax, (%eax)
0x0040a142:	pushl $0xbc70<UINT32>
0x0040a147:	pushl $0x0<UINT8>
0x0040a149:	call GlobalAlloc@kernel32.dll
0x0040a14c:	movl %ecx, %esp
0x0040a14e:	leal %esp, 0xbc70(%eax)
0x0040a154:	movl 0x2e(%ecx), %esp
0x0040a157:	pushl %ebx
0x0040a158:	pushl $0x400000<UINT32>
0x0040a15d:	pushl %ecx
0x0040a15e:	movl %edi, 0x4(%esp)
0x0040a162:	movl %esi, (%ebx)
0x0040a164:	addw %di, $0x780<UINT16>
0x0040a169:	leal %esi, 0x8(%esi,%ebx)
0x0040a16d:	movl (%ebx), %edi
0x0040a16f:	pushl %ebx
0x0040a170:	movl %ebx, 0x10(%esi)
0x0040a173:	movl %eax, $0x880<UINT32>
0x0040a178:	pushl %esi
0x0040a179:	pushl $0x2<UINT8>
0x0040a17b:	pushl %eax
0x0040a17c:	pushl %edi
0x0040a17d:	pushl $0x6<UINT8>
0x0040a17f:	pushl $0xa<UINT8>
0x0040a181:	pushl %esi
0x0040a182:	pushl $0x4<UINT8>
0x0040a184:	pushl %eax
0x0040a185:	pushl %edi
0x0040a186:	call VirtualProtect@kernel32.dll
VirtualProtect@kernel32.dll: API Node	
0x0040a188:	subl %esi, $0x8<UINT8>
0x0040a18b:	popl %ecx
0x0040a18c:	rep movsl %es:(%edi), %ds:(%esi)
0x0040a18e:	popl %ecx
0x0040a18f:	addw %di, $0x58<UINT8>
0x0040a193:	addl %esi, $0x90<UINT32>
0x0040a199:	rep movsl %es:(%edi), %ds:(%esi)
0x0040a19b:	call VirtualProtect@kernel32.dll
0x0040a19d:	popl %eax
0x0040a19e:	leal %ebx, 0x1644(%eax)
0x0040a1a4:	pushl %eax
0x0040a1a5:	addb (%esp), $0xc<UINT8>
0x0040a1a9:	pushl %eax
0x0040a1aa:	addb (%esp), $0x46<UINT8>
0x0040a1ae:	pushl %eax
0x0040a1af:	addb (%esp), $0x65<UINT8>
0x0040a1b3:	pushl %eax
0x0040a1b4:	addb (%esp), $0xffffffa1<UINT8>
0x0040a1b8:	pushl %eax
0x0040a1b9:	addb (%esp), $0xffffffbf<UINT8>
0x0040a1bd:	movl %ecx, (%ebx)
0x0040a1bf:	addl %ebx, $0x14<UINT8>
0x0040a1c2:	movl %edx, -16(%ebx)
0x0040a1c5:	testl %edx, %edx
0x0040a1c7:	je -12
0x0040a1c9:	movl %eax, 0x18(%esp)
0x0040a1cd:	leal %esi, (%ecx,%eax)
0x0040a1d0:	movl %ebp, 0x1c(%esp)
0x0040a1d4:	movl %ebp, 0x8(%ebp)
0x0040a1d7:	movl %ecx, -4(%ebx)
0x0040a1da:	movl %edi, %ebp
0x0040a1dc:	rep movsl %es:(%edi), %ds:(%esi)
0x0040a1de:	movl %esi, %ebp
0x0040a1e0:	movl %edi, -12(%ebx)
0x0040a1e3:	addl %edi, %eax
0x0040a1e5:	pushl %ebx
0x0040a1e6:	pushl %edx
0x0040a1e7:	pushl %edi
0x0040a1e8:	pushl %ebp
0x0040a1e9:	call 0x0040a21b
0x0040a21b:	pushl %ebp
0x0040a21c:	movl %ebp, %esp
0x0040a21e:	movb (%edi), %al
0x0040a220:	subl %esp, $0xbad8<UINT32>
0x0040a226:	leal %ecx, -32888(%ebp)
0x0040a22c:	orl -20(%ebp), $0xffffffff<UINT8>
0x0040a230:	movl -112(%ebp), %ecx
0x0040a233:	leal %ecx, -32888(%ebp)
0x0040a239:	movl -116(%ebp), %ecx
0x0040a23c:	movl %ecx, 0x8(%ebp)
0x0040a23f:	leal %eax, -120(%ebp)
0x0040a242:	pushl %ebx
0x0040a243:	movl -120(%ebp), %eax
0x0040a246:	movl 0x8(%ebp), %ecx
0x0040a249:	pushl %esi
0x0040a24a:	xorl %eax, %eax
0x0040a24c:	xorl %ebx, %ebx
0x0040a24e:	leal %ecx, -32888(%ebp)
0x0040a254:	pushl %edi
0x0040a255:	movl -46680(%ebp), %eax
0x0040a25b:	movl -41536(%ebp), %eax
0x0040a261:	movl -41532(%ebp), %eax
0x0040a267:	movl -8(%ebp), %eax
0x0040a26a:	movl -16(%ebp), %ebx
0x0040a26d:	xorl %edi, %edi
0x0040a26f:	movl -12(%ebp), %ecx
0x0040a272:	movl -4(%ebp), $0x8000<UINT32>
0x0040a279:	testl %eax, %eax
0x0040a27b:	jne 113
0x0040a27d:	pushl $0x3<UINT8>
0x0040a27f:	popl %esi
0x0040a280:	cmpl %edi, %esi
0x0040a282:	jae 36
0x0040a284:	pushl $0xa<UINT8>
0x0040a286:	popl %ecx
0x0040a287:	subl %ecx, %edi
0x0040a289:	shrl %ecx, $0x3<UINT8>
0x0040a28c:	addl -8(%ebp), %ecx
0x0040a28f:	movl %ecx, 0x8(%ebp)
0x0040a292:	movzbl %edx, (%ecx)
0x0040a295:	movl %ecx, %edi
0x0040a297:	addl %edi, $0x8<UINT8>
0x0040a29a:	shll %edx, %cl
0x0040a29c:	orl %ebx, %edx
0x0040a29e:	incl 0x8(%ebp)
0x0040a2a1:	cmpl %edi, %esi
0x0040a2a3:	jb -22
0x0040a2a5:	movl -16(%ebp), %ebx
0x0040a2a8:	movl %ecx, %ebx
0x0040a2aa:	andl %ecx, $0x7<UINT8>
0x0040a2ad:	movl %edx, %ecx
0x0040a2af:	shrl %ecx
0x0040a2b1:	andl %edx, $0x1<UINT8>
0x0040a2b4:	subl %ecx, $0x0<UINT8>
0x0040a2b7:	movl -41540(%ebp), %edx
0x0040a2bd:	je 0x0040a2d2
0x0040a2d2:	subl %edi, %esi
0x0040a2d4:	pushl $0x1<UINT8>
0x0040a2d6:	movl %ecx, %edi
0x0040a2d8:	popl %eax
0x0040a2d9:	andl %ecx, $0x7<UINT8>
0x0040a2dc:	shrl %ebx, $0x3<UINT8>
0x0040a2df:	shrl %ebx, %cl
0x0040a2e1:	subl %edi, %ecx
0x0040a2e3:	movl -16(%ebp), %ebx
0x0040a2e6:	movl -46680(%ebp), %eax
0x0040a2ec:	jmp 0x0040a2f4
0x0040a2f4:	cmpl %eax, $0x1<UINT8>
0x0040a2f7:	jne 96
0x0040a2f9:	cmpl %edi, $0x20<UINT8>
0x0040a2fc:	jae 34
0x0040a2fe:	pushl $0x27<UINT8>
0x0040a300:	popl %eax
0x0040a301:	subl %eax, %edi
0x0040a303:	shrl %eax, $0x3<UINT8>
0x0040a306:	addl -8(%ebp), %eax
0x0040a309:	movl %eax, 0x8(%ebp)
0x0040a30c:	movl %ecx, %edi
0x0040a30e:	addl %edi, $0x8<UINT8>
0x0040a311:	movzbl %eax, (%eax)
0x0040a314:	shll %eax, %cl
0x0040a316:	orl %ebx, %eax
0x0040a318:	incl 0x8(%ebp)
0x0040a31b:	cmpl %edi, $0x20<UINT8>
0x0040a31e:	jb 0x0040a309
0x0040a320:	movl %eax, %ebx
0x0040a322:	notl %ebx
0x0040a324:	andl %eax, $0xffff<UINT32>
0x0040a329:	shrl %ebx, $0x10<UINT8>
0x0040a32c:	xorl %ebx, %eax
0x0040a32e:	jne 3738
0x0040a334:	xorl %edi, %edi
0x0040a336:	xorl %ebx, %ebx
0x0040a338:	testl %eax, %eax
0x0040a33a:	movl -46676(%ebp), %eax
0x0040a340:	movl -16(%ebp), %ebx
0x0040a343:	je 5
0x0040a345:	pushl $0x2<UINT8>
0x0040a347:	popl %eax
0x0040a348:	jmp 0x0040a353
0x0040a353:	movl -46680(%ebp), %eax
0x0040a359:	cmpl %eax, $0x2<UINT8>
0x0040a35c:	jne 285
0x0040a362:	cmpl -4(%ebp), $0x0<UINT8>
0x0040a366:	jne 0x0040a42f
0x0040a42f:	movl %esi, -46676(%ebp)
0x0040a435:	andl -20(%ebp), $0x0<UINT8>
0x0040a439:	cmpl %esi, -4(%ebp)
0x0040a43c:	jbe 0x0040a441
0x0040a441:	pushl %esi
0x0040a442:	pushl 0x8(%ebp)
0x0040a445:	pushl -12(%ebp)
0x0040a448:	call 0x0040a1fc
0x0040a1fc:	pushl %esi
0x0040a1fd:	pushl %edi
0x0040a1fe:	movl %edi, 0xc(%esp)
0x0040a202:	movl %esi, 0x10(%esp)
0x0040a206:	movl %ecx, 0x14(%esp)
0x0040a20a:	sarl %ecx, $0x2<UINT8>
0x0040a20d:	rep movsl %es:(%edi), %ds:(%esi)
0x0040a20f:	movl %ecx, 0x14(%esp)
0x0040a213:	andl %ecx, $0x3<UINT8>
0x0040a216:	rep movsb %es:(%edi), %ds:(%esi)
0x0040a218:	popl %edi
0x0040a219:	popl %esi
0x0040a21a:	ret

0x0040a44d:	addl 0x8(%ebp), %esi
0x0040a450:	addl -8(%ebp), %esi
0x0040a453:	addl -12(%ebp), %esi
0x0040a456:	subl -4(%ebp), %esi
0x0040a459:	addl %esp, $0xc<UINT8>
0x0040a45c:	subl -46676(%ebp), %esi
0x0040a462:	jne 21
0x0040a464:	movl %eax, -41540(%ebp)
0x0040a46a:	negl %eax
0x0040a46c:	sbbl %eax, %eax
0x0040a46e:	andl %eax, $0x7<UINT8>
0x0040a471:	movl -46680(%ebp), %eax
0x0040a477:	jmp 0x0040a47f
0x0040a47f:	pushl $0x3<UINT8>
0x0040a481:	popl %edx
0x0040a482:	cmpl %eax, %edx
0x0040a484:	jne 0x0040a4d7
0x0040a4d7:	movl %ecx, -46676(%ebp)
0x0040a4dd:	xorl %esi, %esi
0x0040a4df:	cmpl %eax, $0x4<UINT8>
0x0040a4e2:	jne 0x0040a5e2
0x0040a5e2:	cmpl %eax, $0x5<UINT8>
0x0040a5e5:	jne 0x0040a834
0x0040a834:	cmpl %eax, $0x6<UINT8>
0x0040a837:	jne 0x0040b12d
0x0040b12d:	cmpl %eax, $0x7<UINT8>
0x0040b130:	jne 74
0x0040b132:	movl %eax, -12(%ebp)
0x0040b135:	movl -112(%ebp), %eax
0x0040b138:	leal %eax, 0x10(%ebp)
0x0040b13b:	pushl %eax
0x0040b13c:	leal %eax, 0xc(%ebp)
0x0040b13f:	pushl %eax
0x0040b140:	leal %eax, -46680(%ebp)
0x0040b146:	pushl %eax
0x0040b147:	leal %eax, -20(%ebp)
0x0040b14a:	pushl %eax
0x0040b14b:	call 0x0040b57c
0x0040b57c:	pushl %ebp
0x0040b57d:	movl %ebp, %esp
0x0040b57f:	pushl %ecx
0x0040b580:	movl %eax, 0x10(%ebp)
0x0040b583:	pushl %ebx
0x0040b584:	pushl %esi
0x0040b585:	movl %esi, 0xc(%ebp)
0x0040b588:	movl %edx, (%eax)
0x0040b58a:	pushl %edi
0x0040b58b:	movl %ecx, 0xb5e4(%esi)
0x0040b591:	movl %edi, 0xb5e8(%esi)
0x0040b597:	cmpl %ecx, %edi
0x0040b599:	movl -4(%ebp), %edx
0x0040b59c:	movl 0xc(%ebp), %ecx
0x0040b59f:	jbe 0x0040b5a7
0x0040b5a7:	movl %ebx, 0x14(%ebp)
0x0040b5aa:	subl %edi, %ecx
0x0040b5ac:	movl %eax, (%ebx)
0x0040b5ae:	cmpl %edi, %eax
0x0040b5b0:	jbe 0x0040b5b4
0x0040b5b4:	testl %edi, %edi
0x0040b5b6:	je 0x0040b5c3
0x0040b5b8:	movl %eax, 0x8(%ebp)
0x0040b5bb:	cmpl (%eax), $0xffffffff<UINT8>
0x0040b5be:	jne 0x0040b5c3
0x0040b5c3:	subl (%ebx), %edi
0x0040b5c5:	pushl %edi
0x0040b5c6:	pushl %ecx
0x0040b5c7:	pushl %edx
0x0040b5c8:	call 0x0040a1fc
0x0040b5cd:	addl 0xc(%ebp), %edi
0x0040b5d0:	movl %eax, 0xb5e0(%esi)
0x0040b5d6:	addl -4(%ebp), %edi
0x0040b5d9:	addl %esp, $0xc<UINT8>
0x0040b5dc:	cmpl 0xc(%ebp), %eax
0x0040b5df:	jne 0x0040b62c
0x0040b62c:	movl %eax, 0x10(%ebp)
0x0040b62f:	movl %ecx, -4(%ebp)
0x0040b632:	popl %edi
0x0040b633:	movl (%eax), %ecx
0x0040b635:	movl %eax, 0xc(%ebp)
0x0040b638:	movl 0xb5e4(%esi), %eax
0x0040b63e:	popl %esi
0x0040b63f:	popl %ebx
0x0040b640:	leave
0x0040b641:	ret

0x0040b150:	movl %eax, -112(%ebp)
0x0040b153:	addl %esp, $0x10<UINT8>
0x0040b156:	cmpl %eax, -116(%ebp)
0x0040b159:	movl -12(%ebp), %eax
0x0040b15c:	jae 0x0040b166
0x0040b166:	movl %ecx, -120(%ebp)
0x0040b169:	subl %ecx, %eax
0x0040b16b:	cmpl -116(%ebp), %eax
0x0040b16e:	movl -4(%ebp), %ecx
0x0040b171:	jne 19
0x0040b173:	pushl $0x8<UINT8>
0x0040b175:	popl %eax
0x0040b176:	movl -46680(%ebp), %eax
0x0040b17c:	cmpl %eax, $0x8<UINT8>
0x0040b17f:	je 0x0040b194
0x0040b194:	movl -20(%ebp), $0x1<UINT32>
0x0040b19b:	movl %eax, -12(%ebp)
0x0040b19e:	movl -41532(%ebp), %ebx
0x0040b1a4:	movl -41536(%ebp), %edi
0x0040b1aa:	movl -112(%ebp), %eax
0x0040b1ad:	leal %eax, 0x10(%ebp)
0x0040b1b0:	pushl %eax
0x0040b1b1:	leal %eax, 0xc(%ebp)
0x0040b1b4:	pushl %eax
0x0040b1b5:	leal %eax, -46680(%ebp)
0x0040b1bb:	pushl %eax
0x0040b1bc:	leal %eax, -20(%ebp)
0x0040b1bf:	pushl %eax
0x0040b1c0:	call 0x0040b57c
0x0040b1c5:	addl %esp, $0x10<UINT8>
0x0040b1c8:	cmpl -20(%ebp), $0x0<UINT8>
0x0040b1cc:	jnl 0x0040b1d2
0x0040b1d2:	movl %eax, 0x8(%ebp)
0x0040b1d5:	popl %edi
0x0040b1d6:	popl %esi
0x0040b1d7:	popl %ebx
0x0040b1d8:	leave
0x0040b1d9:	ret

0x0040a1ee:	testl %eax, %eax
0x0040a1f0:	je -292
0x0040a1f6:	popl %esp
0x0040a1f7:	popl %eax
0x0040a1f8:	popl %edx
0x0040a1f9:	popl %ebx
0x0040a1fa:	jmp 0x0040a1bd
0x00408055:	null
